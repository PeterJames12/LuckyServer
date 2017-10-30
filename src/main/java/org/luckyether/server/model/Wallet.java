package org.luckyether.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Igor Hnes on 8/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "wallet_newbie")
    private String walletNewbie;

    @Column(name = "wallet_experienced")
    private String walletExperienced;

    @Column(name = "wallet_professional")
    private String walletProfessional;

}
