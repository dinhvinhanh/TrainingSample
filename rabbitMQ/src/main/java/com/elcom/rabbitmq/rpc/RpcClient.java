package com.elcom.rabbitmq.rpc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.logging.Level;
import java.util.logging.Logger;

public class RpcClient {
    @Autowired
    private RabbitTemplate rpcTemplate;

    @Autowired
    @Qualifier(value = "rpcClientExchange")
    private DirectExchange directExchange;

    public void send(int maxNumber){
        for (int start = 0; start <= maxNumber; start++) {
            System.out.println(" [???] Client request for fib(" + start + ")");
            Long response = (Long) rpcTemplate.convertSendAndReceive(directExchange.getName(), "rpc", start);
            //System.out.println(" [ok] Client get '" + response + "'");

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(RpcClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(" [ok] Client get '" + response + "'");
        }
    }
}
