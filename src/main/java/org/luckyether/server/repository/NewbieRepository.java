package org.luckyether.server.repository;

import org.luckyether.server.model.Newbie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Igor Hnes on 10/27/17.
 */
public interface NewbieRepository extends JpaRepository<Newbie, Long> {

    /**
     * @return list if {@link Newbie} entity between given id.
     */
    @Query("select s from Newbie s where s.id between ?1 and ?2")
    List<Newbie> getBetweenId(final Long from, final Long to);

    /**
     * @return count id value.
     */
    @Query("SELECT count(id) FROM Newbie")
    Long countById();
}
