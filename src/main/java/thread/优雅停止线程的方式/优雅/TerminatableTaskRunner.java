package thread.优雅停止线程的方式.优雅;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TerminatableTaskRunner {

    public volatile boolean  isUse = true;

    public AtomicInteger reservations =new AtomicInteger(0);

    public BlockingQueue<Runnable> channels;

    public volatile Thread workerThread;

    public TerminatableTaskRunner(BlockingQueue<Runnable> channels,Thread workerThread){
        this.channels = channels;
        this.workerThread = workerThread;
    }

    public TerminatableTaskRunner(){
        this.channels = new LinkedBlockingQueue<Runnable>();
    }

    public void init(){
        Thread t = workerThread;
        if(null != t){
            t.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        channels.put(task);
        reservations.incrementAndGet();
    }

    public void shutdown(){
        System.out.println("关闭服务中......");
        isUse = false;
        Thread t =workerThread;
        if(null != t){
            t.interrupt();
        }
    }



    class WorkerThread extends Thread{
        @Override
        public void run() {
            Runnable task =null;
            try{
                while(true){
                    //线程不再被需要，且无待处理任务
                    if(!isUse && reservations.get() <=0 ){
                        break;
                    }
                    task = channels.take();
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    reservations.decrementAndGet();
                }
            }catch (InterruptedException e){
                workerThread = null;
            }
            System.out.println("工作线程结束");
        }
    }


//    public static void main(String[] args) {
//        Thread t =new Thread();
//        BlockingQueue<Runnable> channels =new LinkedBlockingQueue<>();
//        TerminatableTaskRunner runner =new TerminatableTaskRunner(channels,t);
//
//        runner.init();
//        try {
//            runner.submit(runnable);
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        runner.shutdown();
//    }
 //   }

}
