package com.fdm.SpringJMSProject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;

import com.fdm.SpringJMSProject.springIntegration.SenderGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/fdm/SpringJMSProject/spring-integration-jms-context.xml"})
public class JmsSpringIntegrationTests {

    @Autowired
    @Qualifier("jms.out")
    private MessageChannel messageChannelOut;

    @Autowired
    @Qualifier("jms.in")
    private PollableChannel messageChannelIn;

    @Autowired
    private SenderGateway gateway;

    @Autowired
    private JmsSimpleReceiver simpleReceiver;

    @Test
    public void testToCheckSpringIntegrationJmsConnection() {
        String str = "hello world";
        Message msg = MessageBuilder.withPayload(str).build();

        //sending message straight on a queue ONLY FOR TESTING!!!! BAD PRACTICE
        messageChannelOut.send(msg);
        System.out.println("received message: " + messageChannelIn.receive());
    }

    @Test
    public void testToCheckConversionWithSpringIntegration() {
        //create serializable object to send
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setFavouriteNumber(1);
        simpleObject.setMessage("hello world!");

        Message msg = MessageBuilder.withPayload(simpleObject).build();

        //sending message straight on a queue ONLY FOR TESTING!!!! BAD PRACTICE
        messageChannelOut.send(msg);
        Message<?> receivedMsg = messageChannelIn.receive();
        System.out.println("Received Message: " + receivedMsg);
    }

    @Test
    public void testToSendMessageThroughGatewayToQueueAndReceive() {
        gateway.sendMyMessage("MyHeaderKey", "My message ... Hello World!");
        Object receivedMsg = messageChannelIn.receive();
        if(receivedMsg instanceof GenericMessage) {
            GenericMessage msg = (GenericMessage) receivedMsg;
            assertEquals("MyHeaderKey", msg.getHeaders().get("myHeaderKey"));
            System.out.println("Message header value: " + msg.getHeaders().get("myHeaderKey"));

            assertEquals("My message ... Hello World!", (String) msg.getPayload());
            System.out.println("Message payload: " + msg.getPayload());

        } else {
            System.out.println(receivedMsg);
            fail("message received was of unexpected type");
        }
    }

    public void setSimpleReceiver(JmsSimpleReceiver simpleReceiver) {
        this.simpleReceiver = simpleReceiver;
    }

    public void setGateway(SenderGateway gateway) {
        this.gateway = gateway;
    }
}
