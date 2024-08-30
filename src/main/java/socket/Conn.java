package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Conn {

    //与客户端连接的socket
    private ServerSocket serverSocket;

    private static final int BUFFER_SIZE = 1024;

    //private byte[] readBuff =new byte[BUFFER_SIZE];

    private ByteBuffer byteBuffer;

    //是否使用
    public  boolean isUse = false;

    private int buffCount=0;

    public Conn(){

        byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    }
    //初始化
    public void init(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    //缓冲区剩余的字节数
    public int buffRemain(){

        return BUFFER_SIZE - buffCount;
    }
    //获取客户端地址
    public String getAdress(){
        if(!isUse){
            return "无法获取地址";
        }
        return serverSocket.getInetAddress().getHostAddress();
    }

    public void close(){
        if(!isUse){
            return;
        }
        System.out.println("断开连接："+getAdress());
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isUse =false;
    }

}
