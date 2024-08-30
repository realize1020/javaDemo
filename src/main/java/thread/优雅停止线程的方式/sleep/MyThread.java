package thread.优雅停止线程的方式.sleep;

public class MyThread extends Thread{

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(2000);
            System.out.println("run end");
        }catch (InterruptedException e){
            System.out.println("在沉睡中被停止！进入了catch!!!!"+this.isInterrupted());
            e.printStackTrace();
        }
    }
    /**
     * run begin
     * main end!!
     * 在沉睡中被停止！进入了catch!!!!false
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.base/java.lang.Thread.sleep(Native Method)
     * 	at com.thread.threadSafe.sleep.stop.MyThread.run(MyThread.java:15)
     * 	如果线程在sleep方法中被打断，那么线程会进入catch语句，并且清除停止状态，变成false
     * 	注意：这里是先sleep()，再interrupt()
     * 	最终的结论：不管这两个方法谁先执行，只要是他们两个方法碰一起了，就必定会出现异常
     */
}
