package socket;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class NIOServer {
    /*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;


    //客户端连接数
    private Conn[] conns;

    //最大连接数
    private int maxConn = 50;

    private int i=0;


    private Map<String, SocketChannel> clientsMap = new HashMap<String, SocketChannel>();

    private Map<SocketChannel, String> currentClientMap = new HashMap<SocketChannel,String>();




    private Charset charset = Charset.forName("UTF-8");

    public NIOServer(int port) throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 检索与此通道关联的服务器套接字
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 进行服务的绑定
        serverSocket.bind(new InetSocketAddress(port));
        // 通过open()方法找到Selector
        selector = Selector.open();
        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Start----4700:");
    }


    // 监听
    private void listen() throws IOException {
        while (true) {
            // 选择一组键，并且相应的通道已经打开
            selector.select();
            // 返回此选择器的已选择键集。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            //System.out.println("Server is listening ...");
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleKey(selectionKey);
            }
        }
    }

    // 处理请求
    private void handleKey(SelectionKey selectionKey) throws IOException {
        // 接受请求
        ServerSocketChannel server = null;
        //SocketChannel client = null;
        String sendText;
        //监听的套接字
       SocketChannel client = null;
        int count=0;
        // 测试此键的通道是否已准备好接受新的套接字连接。
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。
            server = (ServerSocketChannel) selectionKey.channel();
            // 接受到此通道套接字的连接。
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。
            client = server.accept();
            // 配置为非阻塞
            client.configureBlocking(false);

            clientsMap.put(client.getLocalAddress().toString(),client);

            // 注册到selector，等待连接
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下次读取
            receivebuffer.clear();
            //读取服务器发送来的数据到缓冲区中
            count = client.read(receivebuffer);
            if (count > 0) {
                String receiveText = new String( receivebuffer.array(),0,count);
                System.out.println("服务器端接受客户端数据--:"+receiveText);
                client.write(charset.encode("服务器端接受客户端数据--:"+receiveText));
                //client.write(charset.encode("连接成功！"));
                client.register(selector, SelectionKey.OP_WRITE);

                BroadcastToAllClient(selector,receiveText);
                //sendToOthersClient(selector,client,receiveText);

            }
        }
//        else if (selectionKey.isWritable()) {
//            //将缓冲区清空以备下次写入
//            sendbuffer.clear();
//            // 返回为之创建此键的通道。
//            client = (SocketChannel) selectionKey.channel();
//            //向缓冲区中输入数据
//            sendText="收到消息，这里是服务器，你好！"+flag++;
//            sendbuffer.put(sendText.getBytes());
//            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
//            sendbuffer.flip();
//            //输出到通道
//           // client.write(sendbuffer);
//            System.out.println("服务器端向客户端发送数据--："+sendText);
//            client.register(selector, SelectionKey.OP_READ);
//        }
    }

    //获取连接池索引，返回负数表示获取失败
    public int NewIndex(){
        if(conns == null){
            return -1;
        }
        for(int i=0;i<conns.length;i++){
            if(conns[i] ==null){
                conns[i] =new Conn();
                return i;
            }else if(conns[i].isUse ==false){
                return i;
            }
        }
        return -1;
    }


    public void BroadcastToAllClient(Selector selector,String content) throws IOException {
        for(SelectionKey key : selector.keys()){
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel){
                SocketChannel dest = (SocketChannel) targetChannel;
                if(clientsMap.containsKey(dest.getLocalAddress().toString())){
                    continue;
                }
                dest.write(charset.encode(content));
                //sendbuffer.clear();
                //sendbuffer.put((clientsMap.get(i)+":"+content).getBytes());
                //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                //sendbuffer.flip();
                //输出到通道
                //dest.write(sendbuffer);
                //dest.register(selector, SelectionKey.OP_READ);
            }
        }
    }



    /**
     * 发送给除自己以外的所有用户
     *
     * @param selector
     * @param content
     * @throws IOException
     */
    public void sendToOthersClient(Selector selector, SocketChannel oneself,
                                   String content) throws IOException {

        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            if (targetchannel instanceof SocketChannel
                    && targetchannel != oneself) {
                SocketChannel dest = (SocketChannel) targetchannel;
                //dest.write(charset.encode(content));
                dest.write(charset.encode(dest.getLocalAddress().toString()+":"+content));
            }

        }

    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int port = 4700;
        NIOServer server = new NIOServer(port);
        server.listen();
    }
}
