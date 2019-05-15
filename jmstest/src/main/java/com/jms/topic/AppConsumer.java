package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
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
        // Create consumer
        MessageConsumer consumer=session.createConsumer(destination);
        // Create a listener
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try{
                    System.out.println("message received"+textMessage.getText());
                } catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
        // close connection
        // connection.close();  // close after receive the  messages, else keep open
    }

}
