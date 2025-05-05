package com.mission.spring.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail(){
        emailService.sendEmail("umair.sheikh1991@gmail.com",
                "hello",
                "testing the emial service");

    }
}
