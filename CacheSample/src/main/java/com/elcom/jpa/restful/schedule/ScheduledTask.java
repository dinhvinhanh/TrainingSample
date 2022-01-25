package com.elcom.jpa.restful.schedule;

import com.elcom.jpa.restful.controller.UserController;
import com.elcom.jpa.restful.model.User;
import com.elcom.jpa.restful.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ScheduledTask {
    @Autowired
    private UserRepository userRepository;

    @Autowired

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public void scheduleTaskWithFixedRate() {
        // call send email method here

        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();


        //System.out.println(users.toString());

        //for(User user : users) {
        //   LOGGER.info("Print Users List", user.toString());
        //   System.out.println(user.toString());
        //}
    }
}