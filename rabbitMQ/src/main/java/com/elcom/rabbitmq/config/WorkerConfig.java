package com.elcom.rabbitmq.config;

import com.elcom.rabbitmq.rpc.RpcClient;
import com.elcom.rabbitmq.worker.WorkerReceiver;
import com.elcom.rabbitmq.worker.WorkerSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"tut2", "work-queues"})
public class WorkerConfig {
    @Bean("worker_queue")
    public Queue initWorkerQueue() {
        return new Queue("worker_queue");
    }

    private static class ReceiverConfig {
        @Bean
        public WorkerReceiver workerReceiver1() {
            return new WorkerReceiver(1);
        }
        @Bean
        public WorkerReceiver workerReceiver2() {
            return new WorkerReceiver(2);
        }
        @Bean
        public WorkerReceiver workerReceiver3() {
            return new WorkerReceiver(3);
        }
    }

    @Bean
    public WorkerSender workerSender() {
        return new WorkerSender();
    }

}
