package thread;

public class ConstantAndThread {
    public static void main(String[] args) {
        Constants.TOKEN="12345";
        try {
            System.out.println("main睡眠中....");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println("Token的值为：");
            System.out.println(Constants.TOKEN);
        },"Thread-1").start();

    }
}
