package thread.优雅停止线程的方式.线程在sleep中无法被停止;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 线程sleep时，使用线程池启动线程，intercept()无法让线程停止
 * 使用start方法启动线程，intercept()可以让线程池停止
 */
public class NotUseTerminateMarkAsTerminated {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        MyRunnable runnable =new MyRunnable();
        Thread t =new Thread(runnable,"thread-trabble");
        executorService.submit(t);
        //t.start();

        t.interrupt();

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
