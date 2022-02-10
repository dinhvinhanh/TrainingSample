package com.elcom.rabbitmq.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RpcServer {
    @RabbitListener(queues = "rpc_requests")
    public long fibonacci(int n){
        System.out.println(" [-->] Server received request for " + n);
        Long result = fib(n);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(RpcServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" [<--] Server returned " + result);
        return result;
    }

    public Long fib(int n) {
        Long a = 0L, b = 1L, c;
        if (n == 0)
            return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }


}
