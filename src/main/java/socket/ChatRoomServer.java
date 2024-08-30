package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author ysj
 *
 *         服务端房间
 *
 *
 *         网络多客户端聊天室(单聊，群聊)，这里测试的数据格式都是自己自定义的，实际开发中格式有程序自动生成 功能1：客户端通过Java
 *         NIO连接到服务端，支持多客户端的连接
 *         功能2：客户端初次连接时，服务端提示输入昵称，如果昵称已经有人使用，提示重新输入，如果昵称唯一，则登录成功，
 *         之后发送消息都需要按照规定格式带着昵称发送消息
 *         功能3：客户端登录后，发送已经设置好的欢迎信息和在线人数给客户端，并且通知其他客户端该客户端上线
 *         功能4：服务器收到已登录客户端输入内容(内容格式为：xxxtoxxx:xxxxx),则为单聊
 *         功能5：服务器收到已登录客户端输入内容(内容格式为：:xxxxx),则为群聊
 *
 *         功能6：提示用户下线（未实现） 实现方法： （1）首先用户下线后发送一条消息（比如：用户名），服务端用来标识该用户已经下线，
 *         （2）然后socketChannelOnline和userOnline（这里同样用List<Map<String,
 *         SocketChannel>>）找到username相对应的map,然后取出即可
 *
 *         一般来说，服务端的大并发要处以半包，粘包以及压力测试，压力测试
 *
 */

public class ChatRoomServer {

    private static List<Map<String, SocketChannel>> maps = new LinkedList<Map<String, SocketChannel>>();
    private Map<String, SocketChannel> map = new HashMap<String, SocketChannel>();
    private final static String USER_EXIST = "System message: User exist, please change a name!";
    private final static String USER_NOTEXIST = "System message: User not exist, please change a name!";
    private final static String MESSAGE_FORMAT_ERROR = "System message: Can't send a message to yourself!";
    // 在线的SocketChannel
    private HashSet<SocketChannel> socketChannelOnline = new HashSet<SocketChannel>();
    private Charset charset = Charset.forName("UTF-8");
    private Selector selector = null;
    static final int port = 5000;

    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(port));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server is listening ...");

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0)
                continue;
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey sk = (SelectionKey) keyIterator.next();
                keyIterator.remove();
                dealWithSelectionKey(server, sk);
            }
        }
    }

    public void dealWithSelectionKey(ServerSocketChannel server, SelectionKey sk)
            throws IOException {
        if (sk.isAcceptable()) {
            SocketChannel sc = server.accept();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
            sk.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("Server is listening from client :"
                    + sc.socket().getRemoteSocketAddress());
            sc.write(charset.encode("Please input your name："));
        }
        // 处理来自客户端的数据读取请求
        if (sk.isReadable()) {
            SocketChannel sc = (SocketChannel) sk.channel();
            StringBuilder content = new StringBuilder();
            int capacity = 1024;
            ByteBuffer buff = ByteBuffer.allocate(capacity);

            ByteBuffer intBuff = ByteBuffer.allocate(4);

            // 处理半包，和粘包问题
            try {
                sc.read(intBuff);
                intBuff.flip();
                String str = charset.decode(intBuff).toString();
                if (str.substring(0, 3).equals("---")) {
                    capacity = Integer.parseInt(str.substring(3, 4));
                } else if (str.substring(0, 2).equals("--")) {
                    capacity = Integer.parseInt(str.substring(2, 4));
                } else if (str.substring(0, 1).equals("-")) {
                    capacity = Integer.parseInt(str.substring(1, 4));
                } else if (!str.substring(0, 1).equals("-")) {
                    capacity = Integer.parseInt(str.substring(0, 4));
                }
                while (sc.read(buff) > 0) {
                    buff.flip();
                    content.append(charset.decode(buff));

                }
                System.out.println("Server is listening from client "
                        + sc.socket().getRemoteSocketAddress()
                        + " data rev is: " + content);
                sk.interestOps(SelectionKey.OP_READ);
            } catch (IOException io) {
                sk.cancel();
                if (sk.channel() != null) {
                    sk.channel().close();
                }
            }
            if (content.length() > 0) {
                // 注册用户
                if (content.indexOf(":") == -1) {
                    String name = content.toString();
                    if (cheackOut(name)) {
                        sc.write(charset.encode(USER_EXIST));
                    } else {
                        map.put(name, sc);
                        maps.add(map);
                        int num = OnlineNum(selector);
                        // 这里模拟注册完了自动登录
                        socketChannelOnline.add(sc);
                        String message = "welcome " + name
                                + " to chat room! Online numbers:" + num;
                        BroadcastToAllClient(selector, message);
                    }

                } else if (content.indexOf(":") == 0) {
                    // 发送消息给除自己以外的所有用户
                    sendToOthersClient(selector, sc, content.substring(1));

                } else {
                    String[] arrayContent = content.toString().split(":");
                    String[] arrayName = arrayContent[0].toString().split("to");
                    String oneself = arrayName[0];
                    String target = arrayName[1];
                    // 发送消息给特定用户
                    if (arrayContent != null && arrayContent[0] != null) {
                        String message = arrayContent[1];
                        message = oneself + " say: " + message;
                        if (cheackOut(target)) {
                            if (!oneself.equals(target)) {
                                SendToSpecificClient(selector, target, message);
                            } else {
                                sc.write(charset.encode(MESSAGE_FORMAT_ERROR));
                            }
                        } else {
                            sc.write(charset.encode(USER_NOTEXIST));
                        }
                    }
                }
            }
        }
    }

    /**
     * 检测用户是否存在
     *
     * @param name
     * @return
     */
    public boolean cheackOut(String name) {
        boolean isExit = false;
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(name)) {
                isExit = true;
                break;
            }
        }
        return isExit;
    }

    // 统计在线人数
    public static int OnlineNum(Selector selector) {
        int res = 0;
        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            if (targetchannel instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }

    /**
     * 发送给特定的用户
     *
     * @param selector
     * @param name
     * @param content
     * @throws IOException
     */
    public void SendToSpecificClient(Selector selector, String name,
                                     String content) throws IOException {

        SocketChannel desChannel = null;
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).get(name) != null) {
                desChannel = maps.get(i).get(name);
                break;
            }

        }
        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            if (targetchannel instanceof SocketChannel) {
                if (desChannel == null || desChannel.equals(targetchannel)) {
                    SocketChannel dest = (SocketChannel) targetchannel;
                    dest.write(charset.encode(content));
                }
            }

        }

    }

    /**
     * 广播给所有用户
     *
     * @param selector
     * @param content
     * @throws IOException
     */
    public void BroadcastToAllClient(Selector selector, String content)
            throws IOException {

        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            if (targetchannel instanceof SocketChannel) {
                SocketChannel dest = (SocketChannel) targetchannel;
                dest.write(charset.encode(content));
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
                dest.write(charset.encode(content));
            }

        }

    }

    public static void main(String[] args) throws IOException {
        new ChatRoomServer().init();
    }

}
