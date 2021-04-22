package com.franktran.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    private final MailProperties mailProperties;
    private final JavaMailSender mailSender;

    public EmailSenderService(MailProperties mailProperties, JavaMailSender mailSender) {
        this.mailProperties = mailProperties;
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipient, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailProperties.getUsername());
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
            System.out.printf("An email sent from %s to %s%n", mailProperties.getUsername(), recipient);
        } catch (MailException e) {
            System.out.println(String.format("Error occurs when sending email. %s", e.getMessage()));
        }
    }

    public void sendEmailWithAttachment(String recipient, String subject, String content, String attachmentPath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);

            File file = new File(attachmentPath);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

        }
    }
}
