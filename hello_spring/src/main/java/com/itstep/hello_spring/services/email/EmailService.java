package com.itstep.hello_spring.services.email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Конркетная реализация отправки почты
 * Соединение с сервисом отправки и тд и тп
 */
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("yy0808@ukr.net");
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
