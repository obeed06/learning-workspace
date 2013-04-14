package com.fdm.SpringJMSProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class JmsSimpleSender {

    @Autowired
    private Destination destination;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTextMessage(final String textMessage) {
        MessageCreator creator = new MessageCreator() {
            public Message createMessage(Session session) {
                TextMessage message = null;
                try {
                    message = session.createTextMessage();
                    message.setStringProperty("text", textMessage);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return message;
            }
        };

        jmsTemplate.send(destination, creator);
    }

    public void convertAndSend(Object obj) {
        jmsTemplate.convertAndSend(destination, obj);
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }

}