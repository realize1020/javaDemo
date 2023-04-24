package thread4;

public class Test {
    public static void main(String[] args) {
        RequestQueue requestQueue =new RequestQueue();
        new ClientThread(requestQueue,"Wang",312454L).start();
        new ServerThread(requestQueue,"Wang",412454L).start();
    }
}
