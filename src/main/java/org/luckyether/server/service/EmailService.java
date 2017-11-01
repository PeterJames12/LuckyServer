package org.luckyether.server.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Igor Hnes on 11/1/17.
 */
public interface EmailService {

    /**
     * @param message is going to be send to user.
     */
    void sendMessage(SimpleMailMessage message);
}
