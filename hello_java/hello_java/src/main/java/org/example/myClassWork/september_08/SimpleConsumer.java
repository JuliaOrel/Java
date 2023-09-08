package org.example.myClassWork.september_08;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.example.Main;

import java.nio.charset.StandardCharsets;

public class SimpleConsumer {
    private static final String queueName="app.events";
    public static void main(String[]args){
        MyRabbitMQ rabbitMQ=new MyRabbitMQ();
        rabbitMQ.useConsume((consumerTag, delivery)->{
            Object data = DTOObject.toObject(delivery.getBody());
            System.out.println(data);
        });
        Thread t=new Thread(rabbitMQ);
        t.start();
    }

    public static void mainInLine(String[]args){
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("password");
        try(
                Connection connection=factory.newConnection();
                Channel channel=connection.createChannel()
        ){
            System.out.println("Connect to RabbitMQ");
            channel.queueDeclare(queueName, false, false, false, null);

            //До этой точки у меня одинаковый процесс соединения

            DeliverCallback deliverCallback=(consumerTag, delivery)->{
                String data=new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(data);

            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
            while(true){
                try{
                    Thread.sleep(500);
                }catch (Exception e){}
            }
        }catch (Exception e){System.out.println(e.getMessage());}

    }


}
