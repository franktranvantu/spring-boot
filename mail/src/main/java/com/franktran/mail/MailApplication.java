package com.franktran.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication

public class MailApplication {

    private final EmailSenderService emailSenderService;

    public MailApplication(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerEmailSender() {
        emailSenderService.sendEmail(
                "tuvantran225@gmail.com",
                "Hello",
                "Hi Frank, my name is Tu");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerEmailSenderWithAttachment() {
        emailSenderService.sendEmailWithAttachment(
                "tuvantran225@gmail.com",
                "Hello",
                "Hi Frank, my name is Tu",
                "data.txt");
    }

}
