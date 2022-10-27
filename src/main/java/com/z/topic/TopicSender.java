package com.z.topic;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private TopicExchange topic;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (index.incrementAndGet() == keys.length) {
            index.set(0);
        }
        String key = keys[index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.getAndIncrement());
        String message = builder.toString();
        rabbitTemplate.convertAndSend(topic.getName(), key, message);
        System.out.println("[X] Sent: " + message);
    }
}
