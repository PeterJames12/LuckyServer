package org.luckyether.server.repository;

import org.luckyether.server.model.Experienced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Igor Hnes on 10/27/17.
 */
public interface ExperiencedRepository extends JpaRepository<Experienced, Long> {

    /**
     * @return list if {@link Experienced} entity between given id.
     */
    @Query("select s from Experienced s where s.id between ?1 and ?2")
    List<Experienced> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    @Query("SELECT count(id) FROM Experienced")
    Long countById();
}