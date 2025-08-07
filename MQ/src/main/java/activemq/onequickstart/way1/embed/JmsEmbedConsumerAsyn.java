package activemq.onequickstart.way1.embed;

//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.*;

public class JmsEmbedConsumerAsyn {

//    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
//    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
//    private static final String BROKER_URL = "tcp://0.0.0.0:62001";
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
//        MessageConsumer messageConsumer;
//
//        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
//
//        try {
//            connection = connectionFactory.createConnection();
//            connection.start();
//            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//            //创建queue
//            destination = session.createQueue("HelloActiveMqQueue");
//            //创建topic
//            //destination = session.createTopic("EmbedMQ");
//            messageConsumer = session.createConsumer(destination);
//
//            messageConsumer.setMessageListener((message)->{
//                try {
//                    System.out.println(((TextMessage)message).getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
