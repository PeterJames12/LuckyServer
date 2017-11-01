package org.luckyether.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 10/27/17.
 */
@Setter
@Getter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory extends AbstractEntity implements Serializable {

    @Column(name = "ether")
    private String ether;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "data")
    private String date;
    @Column(name = "transaction_hash")
    private String transactionHash;
}
