package com.fdm.SpringJMSProject;

import org.springframework.integration.message.GenericMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class JmsSimpleReceiver {

    private JmsTemplate jmsTemplate;
    private Destination destination;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void receiveAndPrintStringMessage() {
        Message message = jmsTemplate.receive();
        TextMessage textMessage = null;
        if (message instanceof TextMessage)
        {
            textMessage = (TextMessage)message;
            try
            {
                System.out.println(textMessage.getText());
                System.out.println(textMessage.getStringProperty("text"));
            }
            catch (JMSException e)
            {
                e.printStackTrace();
            }
       }
    }

    public Object receiveAndConvert() {
       return jmsTemplate.receiveAndConvert(destination);
    }

    public void displayIncommingMessages(GenericMessage msg) {
        Object receivedObject = msg.getPayload();
        if(receivedObject instanceof SimpleObject) {
            SimpleObject convertedReceivedObj = (SimpleObject) receivedObject;
            System.out.println("favourite number: " + convertedReceivedObj.getFavouriteNumber());
            System.out.println("message: " + convertedReceivedObj.getMessage());
        } else {
            System.out.println(receivedObject);
        }
    }
}
