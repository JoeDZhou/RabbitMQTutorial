package com.z.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import javax.annotation.Resource;

public class WorkerSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello worker!";
        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println("[X] Sent: " + message);
    }
}
