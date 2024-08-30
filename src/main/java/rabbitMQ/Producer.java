package rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("admin");
        factory.setPassword("123");
        //channel 实现了自动关闭close接口
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
            /**
             * 生成一个队列
             * 1、队列名称
             * 2、队列里面的消息是否持久化 默认消息存储在内存中
             * 3、该队列是否只供一个消费者进行消费 是否连接共享true可以多个消费者消费
             * 4、是否自动删除，最后一个消费者断开连接以后该队列是否自动删除 true自动删除
             * 5、其他参数
             */
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "hello world";

            /**
             * 发送一个消息
             * 1、发送到哪个交换机
             * 2、路由的key是哪个
             * 3、其他参数
             * 4、发送消息的消息体
             */
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("消息发送完毕");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
