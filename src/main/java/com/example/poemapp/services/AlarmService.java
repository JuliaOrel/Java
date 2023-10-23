package com.example.poemapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {
    @Autowired
    EmailService emailService;


    public void alarm(String msg) {
        emailService.sendEmail("juliaorel1507@gmail.com", "Alarm: Error in App", msg);

    }

}
