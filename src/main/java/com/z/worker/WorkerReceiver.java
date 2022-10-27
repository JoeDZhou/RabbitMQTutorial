package com.z.worker;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "worker")
public class WorkerReceiver {
    private final int instance;

    public WorkerReceiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receiver(String message) {
        System.out.println("[X" + instance + "] Received: " + message);
    }
}
