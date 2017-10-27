package org.luckyether.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Andre on July 2017.
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends AbstractEntity implements Serializable {

    private static final long SERIAL_VERSION_UID = 3024694618038446115L;
    private static final int ROLE_LENGTH = 50;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "name", length = ROLE_LENGTH, nullable = false, unique = true)
    private String name;
}
