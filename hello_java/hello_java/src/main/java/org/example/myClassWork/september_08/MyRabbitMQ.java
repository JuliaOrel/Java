package org.example.myClassWork.september_08;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.Serializable;

public class MyRabbitMQ  implements  Runnable{
    private String queueName="app.events";
    private Channel channel;
    private Connection connection;
    private ConnectionFactory factory;
    DeliverCallback deliverCallback;
    public MyRabbitMQ(){
        factory=new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");
        try{
            connection=factory.newConnection();
            channel=connection.createChannel();
            Thread.sleep(100);


        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public MyRabbitMQ(String queueName){
        this.queueName=queueName;
    }

    public void useConsume(DeliverCallback cb){
        deliverCallback=cb;
    }
    public void publish(Object o){
        try{
            channel.basicPublish("", queueName, null, DTOObject.toBytes(o));
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
    public void disconnect() throws IOException {
        connection.close();
    }
    @Override
    public void run() {
        try {
            while (true) {
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(); // Выйти из ПО
        }
    }

    }

