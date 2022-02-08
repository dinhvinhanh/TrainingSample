package com.elcom.rabbitmq.worker;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.StopWatch;

import java.io.IOException;

@RabbitListener(queues = "worker_queue")
public class WorkerReceiver {
    private final int instance;

    public WorkerReceiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws InterruptedException, IOException {
        StopWatch watch = new StopWatch();
        watch.start();

        //channel.basicConsume("worker_queue", false, channel.getDefaultConsumer());
        //long deliveryTag = channel.basicGet("worker_queue", false).getEnvelope().getDeliveryTag();
        System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
        doWork(in);
        //channel.basicAck(deliveryTag, false);

        watch.stop();
        System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(10000);
            }
        }
    }
}
