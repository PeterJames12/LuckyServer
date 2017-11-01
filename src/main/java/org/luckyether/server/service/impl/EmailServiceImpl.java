package org.luckyether.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.luckyether.server.service.EmailService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Igor Hnes on 11/1/17.
 */
@PropertySource("classpath:email/email.properties")
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final Integer PORT = 587;

    /**
     * {@inheritDoc}.
     */
    @Async
    @Override
    public void sendMessage(SimpleMailMessage message) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(PORT);
        javaMailSender.setUsername("luckyether12@gmail.com");
        javaMailSender.setPassword("F5cowghY5u");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        javaMailSender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        log.debug("message send to " + Arrays.toString(message.getTo()));
    }
}
