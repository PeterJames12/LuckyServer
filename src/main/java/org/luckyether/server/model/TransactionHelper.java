package org.luckyether.server.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Igor Hnes on 9/5/17.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "transaction_helper")
public class TransactionHelper extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "private_key")
    private String privateKey;
}
