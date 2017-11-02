package org.luckyether.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 8/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet extends AbstractEntity implements Serializable {

    @Column(name = "wallet_newbie")
    private String walletNewbie;

    @Column(name = "wallet_experienced")
    private String walletExperienced;

    @Column(name = "wallet_professional")
    private String walletProfessional;

    @Column(name = "payment_token")
    private String paymentToken;
}
