package org.example.myClassWork.september_08;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.Serializable;

public class MyRabbitMQ  implements  Runnable{
    private String queueName="app.events";
    private Channel channel;
    private Connection connection;
    private ConnectionFactory factory;

    public MyRabbitMQ() {
        this("app.events");
    }

    public MyRabbitMQ(String queueName)
    {
        this.queueName = queueName;

        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    public void disconnect() throws IOException {
        connection.close();
    }

    DeliverCallback deliverCallback;
    public void useConsume(DeliverCallback cb){
        deliverCallback=cb;
    }
    public void publish(Object o, String queueName){
        try{
            channel.basicPublish("", queueName, null, DTOObject.toBytes(o));
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(deliverCallback != null) {
                    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                    });
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    }

