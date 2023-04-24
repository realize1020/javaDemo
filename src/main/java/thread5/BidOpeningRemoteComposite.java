package thread5;

import java.util.Map;

public class BidOpeningRemoteComposite implements DecryptRemoteBidFileListener {


    private Map<String, RemoteSupplierListener> remoteSupplierListenerMap;


    @Override
    public void stopRemoteDecrypt() {

    }

    @Override
    public void refreshProgress(int totalCount, int successCount, int noDecCount, int failedDecCout, int errorCount) {

    }

    @Override
    public void refreshBidOpeningStatus(String supplier, String supplierOpeningResult, boolean hasBidPrice) {
        // 更新最新的供应商
        //supplierMap.put(supplier.getId(), supplier);
        RemoteSupplierListener listener = remoteSupplierListenerMap.get(supplier);
        if(null != listener){
            listener.refreshBidOpeningStatus(supplier, supplierOpeningResult,hasBidPrice);
        }
    }
}
