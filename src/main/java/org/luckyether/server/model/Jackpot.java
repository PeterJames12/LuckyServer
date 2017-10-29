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
@Table(name = "jackpot")
public class Jackpot extends AbstractEntity implements Serializable {

    @Column(name = "address")
    private String address;
    @Column(name = "count")
    private Long count;

}
