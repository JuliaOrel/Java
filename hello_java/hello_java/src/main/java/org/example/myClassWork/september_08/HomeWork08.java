package org.example.myClassWork.september_08;

import com.rabbitmq.client.DeliverCallback;

public class HomeWork08 {
    private static final String queueName = "app.events";

    public static void main(String[] args){
        MyRabbitMQ rabbitMQ_site_createUser = new MyRabbitMQ("site.user.register");
        MyRabbitMQ rabbitMQ_site_updateUser = new MyRabbitMQ("site.user.update");
        MyRabbitMQ rabbitMQ_crm_createCustomer = new MyRabbitMQ("crm.user.create");
        MyRabbitMQ rabbitMQ_crm_updateCustomer = new MyRabbitMQ("crm.customer.update");

        DeliverCallback dc = (consumerTag, delivery) -> {
            Object data = DTOObject.toObject(delivery.getBody());
            System.out.println(data);
        };

        rabbitMQ_site_createUser.useConsume( dc);
        rabbitMQ_site_updateUser.useConsume( dc);
        rabbitMQ_crm_createCustomer.useConsume( dc);
        rabbitMQ_crm_updateCustomer.useConsume( dc);


        new Thread(rabbitMQ_site_createUser).start();
        new Thread(rabbitMQ_site_updateUser).start();
        new Thread(rabbitMQ_crm_createCustomer).start();
        new Thread(rabbitMQ_crm_updateCustomer).start();

    }
}
