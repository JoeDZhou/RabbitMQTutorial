package com.z.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("hello-world")
public class Config {
    @Bean
    public Queue anonymousQueue() {
        return new Queue("hello");
    }

    @Profile("sender")
    @Bean public HelloWorldSender helloWorldSender() {
        return new HelloWorldSender();
    }

    @Profile("receiver")
    @Bean public HelloWorldReceiver helloWorldReceiver() {
        return new HelloWorldReceiver();
    }
}
