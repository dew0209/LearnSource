package rabbitmq.b.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    /*
    * 队列的名字
    * */
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("admin");
        factory.setPassword("123");
        //Channel实现了自动close接口 自动关闭 不需要显示关闭
        Connection connection = factory.newConnection();
        Channel channel1 = connection.createChannel();
        /*
        * 生成一个队列
        * 1.队列名称
        * 2.队列里面的消息是否持久化
        * 3.该队列是否只供一个消费者进行消费 是否进行共享  true 可以多个消费者消费
        * 4.是否自动删除，最后一个消费者端断开连接以后 该队列是否自动删除
        * 5.其他参数
        * */
        channel1.queueDeclare(QUEUE_NAME,false,false,false,null);

    }

}
