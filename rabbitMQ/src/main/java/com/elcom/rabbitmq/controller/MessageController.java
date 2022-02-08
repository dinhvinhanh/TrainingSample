package com.elcom.rabbitmq.controller;

import com.elcom.rabbitmq.config.WorkerConfig;
import com.elcom.rabbitmq.constant.Constant;
import com.elcom.rabbitmq.model.Message;
import com.elcom.rabbitmq.model.MessageStatus;
import com.elcom.rabbitmq.rpc.RpcClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Profile("test")
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private RpcClient rpcClient;


    @PostMapping("/{Name}")
    public String messageOrder(@RequestBody Message message, @PathVariable String Name ) {
        message.setId(UUID.randomUUID().toString());
        MessageStatus messageStatus = new MessageStatus(message, "PROCESS", "Messaging Successfully "+ Name);

        rabbitTemplate.convertAndSend(Constant.EXCHANGE,Constant.ROUTING_KEY, messageStatus);
        return "success!!";
    }


}
