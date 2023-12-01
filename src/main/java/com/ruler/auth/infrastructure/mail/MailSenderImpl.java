package com.ruler.auth.infrastructure.mail;

import com.ruler.auth.application.interfaces.CustomerMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImpl implements CustomerMailService {

    private final JavaMailSender mailSender;
    private final String from;

    public MailSenderImpl(JavaMailSender mailSender, @Value("${email.from}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    @Async
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
