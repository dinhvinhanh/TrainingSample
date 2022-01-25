package com.elcom.jpa.restful;

import com.elcom.jpa.restful.model.User;
import com.elcom.jpa.restful.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;

@SpringBootApplication
@EnableCaching
@Slf4j
public class Sample1Application  implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Sample1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ArrayList<User> users = (ArrayList<User>) userService.findAll();

        log.info("list of users: {}", users.toString());
        log.info("find user with id = 1: {}", userService.findById(1L));
        log.info("find user with id = 1: {}", userService.findById(1L));
        log.info("find user with id = 2: {}", userService.findById(2L));
        log.info("find user with id = 2: {}", userService.findById(2L));

        log.info("list of users: {}", users);
        log.info("list of users: {}", users);

        userService.clearCache();
        log.info("find user with id = 1: {}", userService.findById(1L));
        log.info("find user with id = 2: {}", userService.findById(2L));

    }
}
