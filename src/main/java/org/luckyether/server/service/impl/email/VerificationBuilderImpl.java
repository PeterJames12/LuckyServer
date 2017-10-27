package org.luckyether.server.service.impl.email;

import org.luckyether.server.model.Verification;

/**
 * @author Igor Hnes on 8/12/17.
 */
public class VerificationBuilderImpl extends EmailBuilderImpl<Verification> {

    private static final String VERIFICATION_SUBJECT = "Verification";

    @Override
    String getMessageRecipient(Verification verification) {
        return verification.getEmail();
    }

    @Override
    String getMessageBody(Verification verification) {
        return "Dear "
                + "EtherUser"
                + "\n"
                + "verificate password recovery by this code "
                + verification.getCode();
    }

    @Override
    String getMessageSubject() {
        return VERIFICATION_SUBJECT;
    }
}
