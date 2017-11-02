package org.luckyether.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Hnes on 8/12/17.
 */
@Getter
@Setter
@Entity
@Table(name = "email_code")
public class Verification extends AbstractEntity implements Serializable {

    @NotNull
    @Column(name = "email")
    private String email;
    @Column(name = "code")
    private int code;
}
