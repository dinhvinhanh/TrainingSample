package com.elcom.sample1.schedule;

import com.elcom.sample1.controller.UserController;
import com.elcom.sample1.model.Mail;
import com.elcom.sample1.model.User;
import com.elcom.sample1.repository.UserRepository;
import com.elcom.sample1.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ScheduledTask {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleEmailTask() {
        // call send email method here

        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();

        Mail mail = new Mail();
        mail.setMailFrom("vinhanhlun@gmail.com");
        mail.setMailTo("vinhanh.uet@gmail.com");
        mail.setMailSubject("Spring Boot - Email Example");
        mail.setMailContent(users.toString());
        mailService.sendMail(mail);

        //System.out.println(users.toString());

        //for(User user : users) {
        //   LOGGER.info("Print Users List", user.toString());
        //   System.out.println(user.toString());
        //}
    }
}
