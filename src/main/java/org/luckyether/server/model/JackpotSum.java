package org.luckyether.server.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 11/2/17.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "jackpot_sum")
public class JackpotSum extends AbstractEntity implements Serializable {

    @Column(name = "ether")
    private String ether;
}
