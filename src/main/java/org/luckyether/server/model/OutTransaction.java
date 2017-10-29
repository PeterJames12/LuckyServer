package org.luckyether.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 10/29/17.
 */
@Getter
@Setter
@Entity
@Table(name = "out_transaction")
public class OutTransaction extends AbstractEntity implements Serializable {

    @Column(name = "ether")
    private String ether;
    @Column(name = "data")
    private String data;
    @Column(name = "winner_address")
    private String winnerAddress;
}
