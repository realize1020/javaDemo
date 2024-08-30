package thread8;

import java.util.concurrent.Callable;

public class SiteFileFetch2 implements Callable<Boolean> {

    private DownloadFileListener listener;

    public SiteFileFetch2(DownloadFileListener listener) {
        this.listener = listener;
    }

    @Override
    public synchronized Boolean call() {
        System.out.println("模拟下载文件");
        //用一个循环来消耗时间
        int sum=0;
        for(int i=0;i<1000;i++){
            System.out.println(i);
        }
        /*int i = new Random().nextInt(10);
        int result=i % 2;
        if(result == 0){
            //偶数的话，代表下载成功
            listener.complate(true);
        }else{
            //奇数的话，代表下载失败
            listener.complate(false);
        }*/
        listener.complate(true);
        return true;
    }
}
