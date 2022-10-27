package com.z.pubsub;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

public class PubSubSender {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private FanoutExchange fanout;

    AtomicInteger counts = new AtomicInteger(0);
    AtomicInteger dots = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage() {
        StringBuilder builder = new StringBuilder("Hello Pub-Sub: ");
        if (dots.getAndIncrement() == 3){
            dots.set(1);
        }

        for (int i = 0; i < dots.get(); i++) {
            builder.append(".");
        }

        builder.append(counts.incrementAndGet());
        String message = builder.toString();
        rabbitTemplate.convertAndSend(fanout.getName(), "", message);
        System.out.println("[X] Sent: " + message);
    }
}
