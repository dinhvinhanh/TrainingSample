package com.elcom.rabbitmq.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier(value = "hello")
    private Queue queue;

    public void send(String message) {
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sender " + queue.getName() + " queue sent '" + message + "'");
    }

}
