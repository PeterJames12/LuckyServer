package org.luckyether.server.repository;

import org.luckyether.server.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Igor Hnes on 10/27/17.
 */
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    /**
     * @return list if {@link Professional} entity between given id.
     */
    @Query("select s from Professional s where s.id between ?1 and ?2")
    List<Professional> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    @Query("SELECT count(id) FROM Professional")
    Long countById();
}
