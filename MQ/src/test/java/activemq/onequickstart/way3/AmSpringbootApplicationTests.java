package activemq.onequickstart.way3;


import activemq.onequickstart.way3.queue.ProducerQueue;
import activemq.onequickstart.way3.replyto.ProducerR;
import activemq.onequickstart.way3.topic.ProducerTopic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmSpringbootApplicationTests {

    @Autowired
    private ProducerQueue producerQueue;

    @Autowired
    private ProducerTopic producerTopic;

    @Autowired
    private ProducerR producerR;

    @Test
    public void testQueueNormal() throws InterruptedException {
        Destination destination
                = new ActiveMQQueue("springboot.queue");
        for(int i=0; i < 10; i++){
            producerQueue.sendMessage(destination,
                    "NO:"+i+";my name is Mark!!!");
        }
        Thread.sleep(10000);
    }

    @Test
    public void testTopicNormal() throws InterruptedException {
        Destination destination
                = new ActiveMQTopic("springboot.topic");
        for(int i=0; i<3; i++){
            producerTopic.sendMessage(destination,
                    "NO:"+i+";James like 13 !!!");
        }
        Thread.sleep(10000);
    }


    @Test
    public void testReplyNormal() throws InterruptedException {
        Destination destination
                = new ActiveMQQueue("springboot.replyto.queue");
        for(int i=0; i<3; i++){
            producerTopic.sendMessage(destination,
                    "NO:"+i+";James like 13 !!!");
        }
        Thread.sleep(10000);
    }




}
