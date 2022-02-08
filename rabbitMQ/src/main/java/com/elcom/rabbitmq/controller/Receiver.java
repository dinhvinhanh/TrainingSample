package com.elcom.rabbitmq.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    public void receive(String in, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println(" [x] Received '" + in + "'");
    }

}
