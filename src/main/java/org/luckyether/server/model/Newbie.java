package org.luckyether.server.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Igor Hnes on 10/27/17.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "newbie")
public class Newbie extends AbstractEntity implements Serializable {

    @Column(name = "address")
    private String address;
}
