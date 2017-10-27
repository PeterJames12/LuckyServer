package org.luckyether.server.dto;

import lombok.Getter;

/**
 * @author Andre on July 2017.
 */
@Getter
public class UserDTO {

    private String email;
    private Boolean enabled;
    private String password;
    private String wallet;

    public UserDTO() {
    }

    public UserDTO(String email, Boolean enabled, String wallet) {
        super();
        this.email = email;
        this.enabled = enabled;
        this.wallet = wallet;
    }
}