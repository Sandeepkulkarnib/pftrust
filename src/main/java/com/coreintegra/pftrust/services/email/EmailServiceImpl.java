package com.coreintegra.pftrust.services.email;

import com.coreintegra.pftrust.entities.base.EmailAttachment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("rayees.shaikh@coreintegra.com");

            emailSender.send(message);

        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
                                               String... templateArgs) {

        String text = String.format(template.getText(), templateArgs);
        sendSimpleMessage(to, subject, text);

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, List<EmailAttachment> attachments) {
        try {

            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("rayees.shaikh@coreintegra.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            attachments.forEach(emailAttachment -> {
                try {
                    helper.addAttachment(emailAttachment.getName(), emailAttachment.getInputStreamResource());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SendEmail(String to, String subject, String body) {

        try {

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("rayees.shaikh@coreintegra.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
