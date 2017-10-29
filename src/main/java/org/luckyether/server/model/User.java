package org.luckyether.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Andre on July 2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {

    private static final long SERIAL_VERSION_UID = 5502972351616620915L;
    private static final int USERNAME_LENGTH = 50;
    private static final int WALLET_LENGTH = 256;
    private static final int PASSWORD_LENGTH = 256;

    @NotNull
    @Column(name = "email", length = USERNAME_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = PASSWORD_LENGTH)
    private String password;

    @Column(name = "enabled", columnDefinition = "tinyint default 0", length = 1)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;
}
