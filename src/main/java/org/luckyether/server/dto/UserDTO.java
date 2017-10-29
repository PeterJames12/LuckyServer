package org.luckyether.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Andre on July 2017.
 */
@NoArgsConstructor
@Getter
public class UserDTO {

    private String email;
    private Boolean enabled;
    private String password;

    public UserDTO(String email, Boolean enabled) {
        super();
        this.email = email;
        this.enabled = enabled;
    }
}