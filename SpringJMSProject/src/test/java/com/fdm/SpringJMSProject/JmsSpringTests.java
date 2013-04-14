package com.fdm.SpringJMSProject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/fdm/SpringJMSProject/simple-spring-jms-context.xml"})
public class JmsSpringTests {

    @Autowired
    private JmsSimpleSender simpleSender;

    @Autowired
    private JmsSimpleReceiver simpleReceiver;

    @Test
    public void sendStringMessage() {

        simpleSender.convertAndSend("Hello World!");

        Object receivedMessage = simpleReceiver.receiveAndConvert();

        if(receivedMessage instanceof String) {
            String msg = (String) receivedMessage;
            System.out.println(msg);
            assertEquals("Hello World!", msg);
        } else {
            System.out.println(receivedMessage);
            fail("received message was not of expected type");
        }
    }

    @Test
    public void testObjectSendAndReceive() {

        //create object to send (object extends serializable)
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setFavouriteNumber(1);
        simpleObject.setMessage("hello world!");

        //this method converts the SimpleObject to MessageType automatically and then sends it down the queue
        simpleSender.convertAndSend(simpleObject);

        //receiver converts the object back to an object type
        Object receivedObject = simpleReceiver.receiveAndConvert();

        //check the object is of the type we are expecting
        if(!(receivedObject instanceof SimpleObject)) {
            System.out.println(receivedObject);
            fail("object was not an instance of SimpleObject");
        }

        System.out.println(receivedObject);

        SimpleObject convertedReceivedObj = (SimpleObject) receivedObject;
        assertEquals("hello world!", convertedReceivedObj.getMessage());
        System.out.println("message = " + convertedReceivedObj.getMessage());
        assertEquals(1, convertedReceivedObj.getFavouriteNumber());
        System.out.println("favourite number = " +convertedReceivedObj.getFavouriteNumber());
    }


    public void setSimpleReceiver(JmsSimpleReceiver simpleReceiver) {
        this.simpleReceiver = simpleReceiver;
    }

    public void setSimpleSender(JmsSimpleSender simpleSender) {
        this.simpleSender = simpleSender;
    }

}
