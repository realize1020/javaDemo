package thread.优雅停止线程的方式.优雅;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class test {

    public static void main(String[] args) {
        MyRunnable runnable =new MyRunnable();

        Thread t =new Thread();
        BlockingQueue<Runnable> channels =new LinkedBlockingQueue<>();
        TerminatableTaskRunner runner =new TerminatableTaskRunner(channels,t);
        try {
            runner.submit(()->{
                Runnable task =null;
                try{
                    while(true){
                        //线程不再被需要，且无待处理任务
                        if(!runner.isUse && runner.reservations.get() <=0 ){
                            break;
                        }
                        task = channels.take();
                        try {
                            task.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runner.reservations.decrementAndGet();
                    }
                }catch (InterruptedException e){
                    runner.workerThread = null;
                }
                System.out.println("工作线程结束");
            });
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.init();

        //runner.shutdown();
    }

}
class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("线程开始运行.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            System.out.println("sleep后的线程中断状态："+Thread.currentThread().isInterrupted());
            System.out.println("sleep后的线程状态："+Thread.currentThread().getState());
            System.out.println("线程已停止");
        }
        System.out.println("线程运行结束");
    }
}


