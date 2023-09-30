package com.itstep.hello_spring.jobs;

import com.itstep.hello_spring.services.email.EmailService;

import java.util.Date;

public class EmailJob implements Runnable{
    private final EmailService emailService;
    private final String to;
    private final String subject;
    private final String text;
    public EmailJob(
            EmailService emailService,
            String to,
            String subject,
            String text
    ) {
        this.emailService = emailService;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String sendAt = (new Date()).toString();

        emailService.sendEmail(to, subject, text + "Send at: " + sendAt);
    }
}
