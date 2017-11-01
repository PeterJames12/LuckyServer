package org.luckyether.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Andre on July 2017.
 */
@NoArgsConstructor
@Getter
public class UserDTO {

    private Long id;
    private String email;
    private Boolean enabled;
    private String password;
    private String wallet;

    public UserDTO(String email, Boolean enabled, Long id, String password, String wallet) {
        super();
        this.id = id;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.wallet = wallet;
    }
}