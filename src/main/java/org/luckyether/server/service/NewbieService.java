package org.luckyether.server.service;

import org.luckyether.server.model.Newbie;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface NewbieService {

    /**
     * Save {@link Newbie} entity.
     */
    void save(Newbie newbie);

    /**
     * @return list if {@link Newbie} entity between given id.
     */
    List<Newbie> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    Long countById();
}
