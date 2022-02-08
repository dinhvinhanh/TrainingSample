package com.elcom.rabbitmq.controller;

import com.elcom.rabbitmq.worker.WorkerSender;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Profile("tut2")
public class WorkerController {

    private final WorkerSender workerSender;

    public WorkerController(WorkerSender workerSender) {
        this.workerSender = workerSender;
    }

    @GetMapping("/worker/{number}")
    public void workerTest(@PathVariable int number) {
        workerSender.send("test", number);

    }
}
