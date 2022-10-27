package com.z.routing;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

public class RoutingSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private DirectExchange direct;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "green", "black"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (index.incrementAndGet() == 3) {
            index.set(0);
        }
        String key = keys[index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.getAndIncrement());
        String message = builder.toString();
        rabbitTemplate.convertAndSend(direct.getName(), key, message);
        System.out.println("[X] Sent: " + message);
    }
}
