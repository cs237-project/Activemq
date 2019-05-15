package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {
    private static final String url = "tcp://192.168.31.10:61616";
    private static final String topicName="topic-test";

    public static void main(String[] args) throws JMSException {
        // ConnectionFactory
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
        // Connection
        Connection connection = connectionFactory.createConnection();
        // Start connection
        connection.start();
        // Create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create destination
        Destination destination=session.createTopic(topicName);
        // Create a producer
        MessageProducer producer=session.createProducer(destination);
        for(int i=0; i<100; i++){
            //create message
            TextMessage textMessage=session.createTextMessage("test"+i);
            producer.send(textMessage);
            System.out.println("message sent"+textMessage.getText());
        }
        // close connection
        connection.close();
    }
}
