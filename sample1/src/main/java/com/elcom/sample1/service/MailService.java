package com.elcom.sample1.service;

import com.elcom.sample1.model.Mail;

import javax.mail.MessagingException;

public interface MailService {

    public void textMail(String context);

    public void sendMail(Mail mail);
}
