package activemq.onequickstart.way1.embed;

//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.*;

public class JmsEmbedProducer {

//    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
//    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
//    //private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
//    private static final String BROKER_URL = "tcp://0.0.0.0:62001";
//
//    private static final int MESSAGE_NUM = 3;
//
//    public static void main(String[] args) {
//        //连接工厂
//        ConnectionFactory connectionFactory;
//
//        //连接
//        Connection connection;
//
//        //会话
//        Session session;
//
//        //jms目的
//        Destination destination;
//
//        //jms生产者
//        MessageProducer messageProducer;
//
//
//        try {
//
//            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
//
//
//            connection = connectionFactory.createConnection();
//
//            connection.start();
//            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//
//            //创建queue
//            destination = session.createQueue("HelloActiveMqQueue");
//            //创建topic
//            //destination = session.createTopic("EmbedMQ");
//
//            messageProducer = session.createProducer(destination);
//
//            for(int i = 0;i < MESSAGE_NUM;i ++){
//                String message = "发送消息 " + System.currentTimeMillis();
//                TextMessage txtMessage = session.createTextMessage(message);
//                messageProducer.send(txtMessage);
//                System.out.println(message);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
