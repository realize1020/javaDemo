package thread5;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WhileThread  extends MyRunnable {

    private Thread thread;

    private ThreadPoolExecutor decryptBidFileThreadPool;

    private DecryptRemoteBidFileListener listener;
    private BidPriceResolveListener bidPriceResolveListener;
    private Map<String, String> decryptStatusMap;//private Map<String, Supplier> decryptStatusMap;

    private final static SupplierService supplierService;
    private int totalSupplierCount = 0;
    private int totalNoDecCount = 0;
    private int totalErrorsCount = 0;
    private int totalBidSuccessCount = 0;
    private int totalBidFailCount = 0;
    private boolean isPriceStage = false;
    private boolean isBusAndTecStage = false;

    private String supplier = "无供应商";

    private String status = "无状态";

    private List<String> bidOpeningComplateSupplierIdList; // 开标完成的供应商集合

    static {
        supplierService = new SupplierService();
    }

    @Override
    public void run() {

        try {
            //HttpUrl httpUrl = HttpUtils.getProjectHttpUrl(tenderProject, PropertiesUtils.getWebserviceProperty(WebserviceConstants.GET_SUPPLIER_DECRYPT_STAUTS_METHOD, "bidOpeningHS.do?getSupplierBidOpeningDecryptStauts"));

            String httpUrl = "";


            //查数据库得到得到供应商集合
            List<String> supplierList = supplierService.getSupplierOpenBidList();

            //通过线程池解密文件
            int maxPoolSize = null != supplierList && supplierList.size() < 5 ? supplierList.size() : 5;

            bidPriceResolveListener = new BidPriceResolveListener("tenderProject", maxPoolSize);

            decryptBidFileThreadPool = new ThreadPoolExecutor(maxPoolSize, maxPoolSize, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

            decryptStatusMap = new HashMap<String, String>();//decryptStatusMap = new HashMap<String, Supplier>();
            //。。。。。
            long startTime = 0;
            long useTime = -1;

            while (null != thread) {
                startTime = Calendar.getInstance().getTimeInMillis();
                totalErrorsCount = 0;
                totalBidFailCount = 0;
                totalBidSuccessCount = 0;
                totalSupplierCount = 0;
                totalNoDecCount = 0;
                System.out.println("获取供应商远程解密状态中...");
                if (null != supplierList && supplierList.size() > 0) {
                    Set<String> supplierOrgCodeSet = new HashSet<String>();

                    for (String supplier : supplierList) {

                        if (null == thread) {
                            break;
                        }
                        if (supplier.equals("")) {
                            continue;
                        }

                        String supplierOpeningResult = "开标结果：";
                        supplierOpeningResult = supplier;
                        String decsFlag = "";
                        boolean hasBidPrice = false;

                        if (!supplierOpeningResult.equals("")) {
                            hasBidPrice = true;
                        }

                        // 判断投标人是否废标
                        if (!supplierOpeningResult.contains("废标")) {
                            refreshBidOpeningStatus(supplier, supplierOpeningResult, hasBidPrice, true);
                            continue;
                        }

                        if (isPriceStage) {
                            //模拟价格标状态不为null和价格标开标成功
                            if (supplierOpeningResult.contains("价格标开标成功")) {

                                decsFlag = "价格标开标成功";

                            }else if(supplierOpeningResult.contains("价格标开标失败")){

                                decsFlag = "价格标开标失败";

                            }


                        }else if (isBusAndTecStage) {
                            //模拟商务标状态不为null和商务标开标成功
                            if (supplierOpeningResult.contains("商务标开标成功")) {

                                decsFlag = "商务标开标成功";

                            }else if(supplierOpeningResult.contains("商务标开标失败")){

                                decsFlag = "商务标开标失败";

                            }


                        }else{
                            //模拟技术标状态不为null和价格标开标成功
                            if (supplierOpeningResult.contains("技术标开标成功")) {

                                decsFlag = "技术标开标成功";

                            }else if(supplierOpeningResult.contains("技术标开标失败")){

                                decsFlag = "技术标开标失败";

                            }


                        }

                        totalSupplierCount ++;
                        if(decsFlag.contains("开标成功")){

                            //开标成功

                            boolean isRealComplate = true;
                            totalBidSuccessCount ++;

                            if(!isBusAndTecStage){
                                // 如果是非资审项目，并且没有报价，则标记为错
                                if(!hasBidPrice){
                                    totalErrorsCount ++;
                                    isRealComplate = false;// 如果开标成功，却没有报价，则下次继续刷新
                                }
                            }

                            refreshBidOpeningStatus(supplier, supplierOpeningResult, hasBidPrice, isRealComplate);

                            if(isRealComplate){

                                continue;

                            }


                        }else if(decsFlag.contains("开标失败")){
                            totalBidFailCount ++;
                            totalErrorsCount ++;
                            boolean isRealComplate = false;
                            if(isPriceStage){
                                // 如果已经确认了解密失败，则记住，不需要重复刷新
                                if(supplierOpeningResult.contains("价格标开标失败")){
                                    isRealComplate = true;
                                }
                            }else {
                                if(supplierOpeningResult.contains("价格标开标失败")){
                                    isRealComplate = true;
                                }
                            }

                            refreshBidOpeningStatus(supplier,supplierOpeningResult,hasBidPrice,isRealComplate);


                        }else{

                            totalNoDecCount ++;
                        }

                        supplierOrgCodeSet.add(supplier);

                        if(null!=listener){
                            listener.refreshProgress(totalSupplierCount, totalBidSuccessCount, totalNoDecCount, totalBidFailCount, totalErrorsCount);
                        }

                        // 如果存在需要查询解密状态的供应商则调用接口
                        if(supplierOrgCodeSet.size() > 0){

                            if(useTime > 5000){
                                // 此次刷新时间超过5秒
                            }else if(useTime > 0){
                                try {
                                    Thread.sleep(5000 - useTime);
                                } catch (InterruptedException e) {

                                }
                            }

                            String getWebserviceXml = null;
                            try {
                                //getWebserviceXml = HttpUtil.post(httpUrl)
                                getWebserviceXml = "{\"msg\":\"\",\"code\":\"\",\"data\":{\"bidOpeningSignInfoList\":[{\"telephoneNumber\":\"\",\"organizationName\":\"程丽芬测试3\",\"signTime\":\"\",\"remark\":\"\",\"legalAgent\":\"\",\"signPerson\":\"\",\"post\":\"\",\"packCode\":\"\",\"caType\":\"\",\"orgCode\":\"2c9099a96ec5b0bd016ec719fb050d99\",\"signPersonCard\":\"\",\"email\":\"\",\"signPersonTel\":\"\",\"enterOpenRoomStatus\":\"01\"},{\"telephoneNumber\":\"\",\"organizationName\":\"汪佳佳测试用供应商ck5\",\"signTime\":\"\",\"remark\":\"\",\"legalAgent\":\"\",\"signPerson\":\"\",\"post\":\"\",\"packCode\":\"\",\"caType\":\"\",\"orgCode\":\"2c9099a96ef3170f016ef418ed9a0d5c\",\"signPersonCard\":\"\",\"email\":\"\",\"signPersonTel\":\"\",\"enterOpenRoomStatus\":\"01\"},{\"telephoneNumber\":\"\",\"organizationName\":\"chenglifen4\",\"signTime\":\"\",\"remark\":\"\",\"legalAgent\":\"\",\"signPerson\":\"\",\"post\":\"\",\"packCode\":\"\",\"caType\":\"\",\"orgCode\":\"2c9099aa6ec9615a016ec9d655cf060a\",\"signPersonCard\":\"\",\"email\":\"\",\"signPersonTel\":\"\",\"enterOpenRoomStatus\":\"01\"}]},\"header\":{\"msg\":\"登陆成功\",\"ret\":\"0\",\"size\":\"\"},\"user\":null}";
                                boolean result =resolveXml(getWebserviceXml);
                                if(result){
                                    System.out.println("解密成功！");
                                }else{
                                    System.out.println("解密失败！");
                                }
                            } catch (Exception e) {
                               //日志记录
                            }
                        }else{
                            break;
                        }

                    }

                    useTime = Calendar.getInstance().getTimeInMillis()-startTime;
                    //全部开标完成
                    if(totalErrorsCount == 0 && totalBidSuccessCount>= totalSupplierCount){
    //					if(totalBidSuccessCount + totalBidFaildCount >= totalSupplierCount){
                        break;
                    }
                    supplierList = supplierService.getSupplierOpenBidList();


                }


            }
        } catch (Exception e) {

        } finally {
            System.out.println("获取供应商远程解密状态结束！");
            if(null != thread && null != listener){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                listener.stopRemoteDecrypt();
            }
            stop();
        }

    }

    private boolean resolveXml(String getWebserviceXml) {
        boolean result = StrUtil.contains(getWebserviceXml, "登陆成功");
        return result;
    }


    /**
     *
     * @param supplier
     * @param supplierOpeningResult
     * @param hasBidPrice
     * @param rememberMe
     */
    public synchronized void refreshBidOpeningStatus(String supplier, String supplierOpeningResult, boolean hasBidPrice, boolean rememberMe) {
        if(null!=thread){
            if(!bidOpeningComplateSupplierIdList.contains(supplier)){
                if(rememberMe){
                    bidOpeningComplateSupplierIdList.add(supplier);
                }
                if(null != listener){
                    listener.refreshBidOpeningStatus(supplier, supplierOpeningResult,hasBidPrice);
                }
            }
        }
    }


    public void start(){
        if(thread!=null){
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop(){
        if(null != decryptBidFileThreadPool){
            decryptBidFileThreadPool.shutdownNow();
        }

        thread = null;

    }


    public void join(){
        if(thread!=null){
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        }
    }


    /*public synchronized  void refreshStatus(String status, List<String> statusList, boolean hasBidPrice){

        if(null != thread){
            if(statusList.contains(status)){
                if(hasBidPrice){
                    statusList.add(status);
                }
            }

            if(null != listener){
                listener.refreshBidOpeningStatus(supplier,status,hasBidPrice);
            }

        }

    }*/




}
