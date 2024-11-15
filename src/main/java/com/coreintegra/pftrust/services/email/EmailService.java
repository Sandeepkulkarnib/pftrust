package com.coreintegra.pftrust.services.email;

import com.coreintegra.pftrust.entities.base.EmailAttachment;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
                                        String ...templateArgs);

    void sendMessageWithAttachment(String to, String subject, String text, List<EmailAttachment> attachments);

    void SendEmail(String to, String subject, String body);

}
