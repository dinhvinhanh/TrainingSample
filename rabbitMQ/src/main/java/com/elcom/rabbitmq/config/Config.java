package com.elcom.rabbitmq.config;

import com.elcom.rabbitmq.helloworld.Receiver;
import com.elcom.rabbitmq.helloworld.Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean("hello")
    public Queue initHelloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
