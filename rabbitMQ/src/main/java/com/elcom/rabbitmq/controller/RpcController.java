package com.elcom.rabbitmq.controller;


import com.elcom.rabbitmq.rpc.RpcClient;
import com.elcom.rabbitmq.worker.WorkerSender;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api")
@Profile("tut3")
public class RpcController {

    private final RpcClient rpcClient;

    public RpcController(RpcClient rpcClient) {
        this.rpcClient = rpcClient;

    }

    @GetMapping ("/fib/{number}")
    public void fibTest(@PathVariable int number)  {
        rpcClient.send(number);

    }


}
