package org.luckyether.server.service;

import org.luckyether.server.model.Professional;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface ProfessionalService {

    /**
     * Save {@link Professional} entity.
     */
    void save(Professional professional);

    /**
     * @return list if {@link Professional} entity between given id.
     */
    List<Professional> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    Long countById();
}
