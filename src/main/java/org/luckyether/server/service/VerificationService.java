package org.luckyether.server.service;

import org.luckyether.server.model.Verification;

/**
 * @author Igor Hnes on 8/12/17.
 */
public interface VerificationService {

    /**
     * Send validate code to user email.
     */
    void verificateUserByCode(String email);

    /**
     * Verification user after entering code.
     *
     * @return if two code are equal.
     */
    String verificateUserAfter(Verification verification);
}
