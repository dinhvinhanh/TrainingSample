package com.elcom.scheduled.email.service;

import com.elcom.scheduled.email.model.Mail;

public interface MailService {

    public void textMail(String context);

    public void sendMail(Mail mail);
}
