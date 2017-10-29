package org.luckyether.server.service;

import org.luckyether.server.model.Experienced;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface ExperiencedService {

    /**
     * Save {@link Experienced} entity.
     */
    void save(Experienced experienced);

    /**
     * @return list if {@link Experienced} entity between given id.
     */
    List<Experienced> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    Long countById();
}
