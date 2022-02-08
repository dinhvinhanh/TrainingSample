package com.elcom.rabbitmq.controller;

import com.elcom.rabbitmq.config.WorkerConfig;
import com.elcom.rabbitmq.constant.Constant;
import com.elcom.rabbitmq.model.Message;
import com.elcom.rabbitmq.model.MessageStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping("/{Name}")
    public String messageOrder(@RequestBody Message message, @PathVariable String Name ) {
        message.setId(UUID.randomUUID().toString());
        MessageStatus messageStatus = new MessageStatus(message, "PROCESS", "Messaging Successfully "+ Name);

        rabbitTemplate.convertAndSend(Constant.EXCHANGE,Constant.ROUTING_KEY, messageStatus);
        return "success!!";
    }

}
