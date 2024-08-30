package socket;

import cn.hutool.core.util.HexUtil;
import sun.security.ec.ECDSASignature;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class UnityServer {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        int serverPoet = 4700;
        ServerSocket serverSocket =null;
        Socket cllientSocket=null;
        int recvMsgSize = 0;

        byte[] receivBuf =new byte[32];

        try {
            serverSocket=new ServerSocket(serverPoet);
            while(true){
                System.out.println("服务端已启动,绑定端口"+serverPoet);

                cllientSocket = serverSocket.accept();

                SocketAddress remoteSocketAddress = cllientSocket.getRemoteSocketAddress();

                System.out.println("收到客户端连接：ip = "+remoteSocketAddress);

                InputStream in = cllientSocket.getInputStream();

                OutputStream out = cllientSocket.getOutputStream();

                while((recvMsgSize = in.read(receivBuf))!=-1){
                    String receivedData = new String(receivBuf,0,recvMsgSize,"UTF-8");
                    System.out.println(receivedData);

                    out.write("java服务器收到消息".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                cllientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
