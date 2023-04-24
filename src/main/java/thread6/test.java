package thread6;

import java.util.concurrent.atomic.AtomicInteger;

public class test {
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread =new Thread(new MyThread());
            thread.start();
        }
    }



}
class MyThread implements Runnable{

    @Override
    public void run() {
        AtomicInteger num =new AtomicInteger(100);

        for(num.intValue();num.intValue()>0;num.decrementAndGet()){
            System.out.println(num.compareAndSet(num.intValue(),num.intValue()-1)+"_"+num.intValue());
        }
    }
}