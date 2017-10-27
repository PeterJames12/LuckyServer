package org.luckyether.server.service.impl.email;

import org.luckyether.server.model.User;

/**
 * @author Igor Hnes on 8/13/17.
 */
public class WinnerBuilderImpl extends EmailBuilderImpl<User> {

    private static final String WINNER_SUBJECT = "You are winner";

    @Override
    String getMessageRecipient(User user) {
        return user.getEmail();
    }

    @Override
    String getMessageBody(User user) {
        return "Dear "
                + "User"
                + "!"
                + "\n"
                + "LuckyEther congratulates You as a Winner!"
                + "\n"
                + "You winnings were ...ETH!"
                + "\n"
                + "Try to win more! Good Luck!"
                + "\n"
                + "Best regards, company LuckyEther site: "
                + "\n"
                + "www.luckyether.org";
    }

    @Override
    String getMessageSubject() {
        return WINNER_SUBJECT;
    }
}
