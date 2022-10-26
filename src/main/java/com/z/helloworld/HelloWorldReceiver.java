package com.z.helloworld;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class HelloWorldReceiver {
    @RabbitHandler
    public void receive(String message) {
        System.out.println("[X] Received: " + message);
    }
}
