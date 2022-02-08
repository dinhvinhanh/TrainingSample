package com.elcom.rabbitmq.worker;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.atomic.AtomicInteger;

public class WorkerSender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier(value = "worker_queue")
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    public void send(String message, int numOfSend) {
        for (int index = 1; index <= numOfSend; index++) {
            StringBuilder builder = new StringBuilder(message);
            if (dots.getAndIncrement() == 3) {
                dots.set(1);
            }
            for (int i = 0; i < dots.get(); i++) {
                builder.append('.');
            }
            builder.append(count.incrementAndGet());
            //Default mode MessageDeliveryMode.PERSISTENT
            template.convertAndSend(queue.getName(), builder.toString());
            //template.convertAndSend(queue.getName(), (Object) builder.toString(), messageDurablePostProcessor);

            //Non persistent
            //template.convertAndSend(queue.getName(), (Object) builder.toString(), messageNoneDurablePostProcessor);

            System.out.println(" [x] WorkerSender " + queue.getName() + " queue sent '" + message + "'");
        }
    }

    final MessagePostProcessor messageDurablePostProcessor = new MessagePostProcessor() {
        @Override
        public Message postProcessMessage(final Message message) throws AmqpException {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        }
    };
    final MessagePostProcessor messageNoneDurablePostProcessor = new MessagePostProcessor() {
        @Override
        public Message postProcessMessage(final Message message) throws AmqpException {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return message;
        }
    };
}
