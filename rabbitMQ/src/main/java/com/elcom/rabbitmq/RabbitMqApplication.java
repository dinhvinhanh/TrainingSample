package com.elcom.rabbitmq;

import com.elcom.rabbitmq.rpc.RpcClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitMqApplication implements CommandLineRunner {

    public static void main(String[] args)  {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //rpcClient.send(100);
    }
}
