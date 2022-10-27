package com.z.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public class TopicReceiver {
    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        handlerMessage(message, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        handlerMessage(message, 2);
    }

    public void handlerMessage(String message, int instance) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("[X" + instance + "] Received " + message);
        doWork(message);
        stopWatch.stop();
        System.out.println("[X" + instance + "] Done work, used time: " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

    private void doWork(String message) throws InterruptedException {
        for (char character : message.toCharArray()) {
            if (character == '.') {
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
