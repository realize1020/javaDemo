package thread.优雅停止线程的方式.sleep;

public class test {
    public static void main(String[] args) throws InterruptedException {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(200);
            myThread.interrupt();
        }catch (InterruptedException e){
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("main end!!");

    }

}
