package callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class DefaultMQProducer {

    public void send(String msg, SendCallback sendCallback){
        System.out.println("msg:"+msg);
        final long beginStartTime = System.currentTimeMillis();
        final long timeout=100000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    long costTime = System.currentTimeMillis() - beginStartTime;
                    if (timeout > costTime) {
                        try {
                            System.out.println();
                        } catch (Exception e) {
                            sendCallback.onException(e);
                        }
                    } else {
                        sendCallback.onException(
                                new Exception("DEFAULT ASYNC send call timeout"));
                    }
                }

            });
        } catch (RejectedExecutionException e) {
            throw new RejectedExecutionException("executor rejected ");
        }
    }
}
