package thread8;

import java.util.concurrent.*;

public class DownloadFileUtill {
    //private SiteFileFetch siteFileFetch;
    private SiteFileFetch2 siteFileFetch;
    //private SiteFileFetch3 siteFileFetch;
    private Thread thread;
    //private static FutureTask<Boolean> futureTask;
    private ExecutorService syncExecutor;

//    public DownloadFileUtill(DownloadFileListener listener) {
//        siteFileFetch = new SiteFileFetch(listener);
//    }

    //不是上面的放实现类adapter，而是下面放接口

    public DownloadFileUtill(DownloadFileListener listener) {
        //siteFileFetch = new SiteFileFetch(listener);
        siteFileFetch = new SiteFileFetch2(listener);
        //thread = new Thread(siteFileFetch);
        //futureTask = new FutureTask<Boolean>(siteFileFetch);
        //thread = new Thread(futureTask);
        syncExecutor = Executors.newFixedThreadPool(3);
    }


    public static  synchronized boolean startDownloadFileSync(String fileURL, String fileName, String filePath, String message) {
        DownloadReportFile downloadReportFile = new DownloadReportFile(){

            private boolean downloadFlag;

            public void complate(boolean loginFlag) {
                this.downloadFlag = loginFlag;
            }

            public boolean isDownload() {
                return downloadFlag;
            }
        };

//        new DownloadFileUtill(downloadReportFile).startDownloadFile();
        Future<Boolean> booleanFuture = new DownloadFileUtill(downloadReportFile).startDownloadFile2();
        boolean result = false;

//        try {
//             result=futureTask.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            result=booleanFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return downloadReportFile.isDownload();
//        return result;

    }

    private void startDownloadFile() {
        //siteFileFetch.start();
        //thread.start();
    }

    private Future<Boolean> startDownloadFile2() {
        Future<Boolean> submit = syncExecutor.submit(siteFileFetch);
        return submit;
    }

//    private Future<Boolean> startDownloadFile2() {
//        //siteFileFetch.start();
//        //thread.start();
//        //Future<Boolean> submit = syncExecutor.submit(siteFileFetch);
//        //return submit;
//    }
}
