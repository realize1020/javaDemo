package thread5;

import java.util.ArrayList;
import java.util.List;

public abstract class MyRunnable implements Runnable{

    public Thread thread;

    private List<MyRunnable> joinThread;

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        thread = null;
    }

    public void join(){
        if(null!=thread){
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        }
    }

    public void addJoinThread(MyRunnable thread){
        if(null == joinThread){
            joinThread = new ArrayList<MyRunnable>();
        }
        joinThread.add(thread);
    }

    public void joinOtherThread(){
        if(null == joinThread){
            for(MyRunnable myRunnable:joinThread){
                myRunnable.join();
            }
        }
    }

}
