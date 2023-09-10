package org.example.myClassWork.September09;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;


public class MyRabbitMQ09 implements  Runnable{
    private String queueName="app.events";
    private Channel channel;
    private Connection connection;
    private ConnectionFactory factory;

    public MyRabbitMQ09() {
        this("app.events");
    }

    public MyRabbitMQ09(String queueName)
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
    public void publish (SerializableToBytes o){
        try{
            channel.basicPublish("", queueName, null, o.toBytes());
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
            System.out.println("Exit");
            //throw new RuntimeException(); // Выйти из ПО
        }
    }

    }

