package com.elcom.sample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Sample1Application {

    public static void main(String[] args) {

        SpringApplication.run(Sample1Application.class, args);
    }

}
