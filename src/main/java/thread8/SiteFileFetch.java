package thread8;

import java.util.Random;

public class SiteFileFetch extends Thread {

    private DownloadFileListener listener;

    public SiteFileFetch(DownloadFileListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        System.out.println("模拟下载文件");
        //用一个循环来消耗时间
        for(int i=0;i<1000;i++){
            i+=2;
            System.out.println("i="+i);
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
    }
}
