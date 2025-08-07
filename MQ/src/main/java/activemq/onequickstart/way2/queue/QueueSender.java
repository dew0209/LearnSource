package activemq.onequickstart.way2.queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component("queueSender")
public class QueueSender {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;
    @Autowired
    private GetResponse getResponse;

    public void send(String queueName, final String message) {
        jmsTemplate.send(queueName, session -> {
            Message msg = session.createTextMessage(message);
            //配置，告诉消费者如何应答
            Destination tempDst = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDst);
            responseConsumer.setMessageListener(getResponse);
            msg.setJMSReplyTo(tempDst);

            String uid = System.currentTimeMillis() + "";
            msg.setJMSCorrelationID(uid);

            return msg;
        });
    }


}
