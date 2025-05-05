package com.mission.spring.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public void sendEmail(String to, String sbj, String body){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setSubject(sbj);
            mailMessage.setText(body);
            javaMailSender.send(mailMessage);
        } catch (Exception e){
            log.error("Exception while sending email" + e);
        }
    }

    @Scheduled(cron = "0 * * ? * *")
    public void checkCron(){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("suleman.zahid@jeeny.me");
            mailMessage.setSubject("Termination");
            mailMessage.setText("you are fired sorry to say");
            javaMailSender.send(mailMessage);
        } catch (Exception e){
            log.error("Exception while sending email" + e);
        }
    }
}
