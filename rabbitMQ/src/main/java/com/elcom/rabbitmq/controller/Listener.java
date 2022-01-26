package com.elcom.rabbitmq.controller;

import com.elcom.rabbitmq.constant.Constant;
import com.elcom.rabbitmq.model.MessageStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    @RabbitListener(queues = Constant.QUEUE )
    public void consumeMessageFromQueue(MessageStatus messageStatus) {
        System.out.println("Message Received from queue: " + messageStatus );
    }
}
