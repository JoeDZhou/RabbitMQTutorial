package com.z.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("worker")
@Configuration
public class Config {
    @Bean
    public Queue anonymousQueue() {
        return new Queue("worker");
    }

    @Profile("receiver")
    private static class ReceiverConfig {
        @Bean
        public WorkerReceiver receiver1() {
            return new WorkerReceiver(1);
        }

        @Bean
        public WorkerReceiver receiver2() {
            return new WorkerReceiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public WorkerSender workerSender() {
        return new WorkerSender();
    }
}
