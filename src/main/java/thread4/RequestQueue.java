package thread4;

import java.util.LinkedList;

public class RequestQueue {

    private final LinkedList<Request> queue =new LinkedList<Request>();

    public synchronized Request getRequest(){
        while(queue.peek()==null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }


    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }

}
