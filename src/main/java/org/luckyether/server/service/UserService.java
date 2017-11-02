package org.luckyether.server.service;

import org.luckyether.server.dto.UserDTO;
import org.luckyether.server.exception.RequestException;

/**
 * @author Andre on July 2017.
 */
public interface UserService {

    /**
     * @param user is going to be create in db.
     * @throws RequestException if something went wrong.
     */
    UserDTO create(UserDTO user) throws RequestException;

    /**
     * @return {@link UserDTO} by email.
     */
    UserDTO getUserByUserEmail(String email);

    /**
     * Sends to email new generated password for existed EtherUser.
     *
     * @param email email for recovering password, must not be {@literal null}.
     */
    void recoverPassword(String email);

    /**
     * Update user.
     */
    void update(UserDTO user);

    /**
     * Set new password for user with given id.
     */
    boolean changePassword(Long id, String password);
}
