package org.luckyether.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 9/5/17.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "transaction_helper")
public class TransactionHelper extends AbstractEntity implements Serializable {

    @Column(name = "newbie_key")
    private String newbieKey;

    @Column(name = "experienced_key")
    private String experiencedKey;

    @Column(name = "professional_key")
    private String professionalKey;
}
