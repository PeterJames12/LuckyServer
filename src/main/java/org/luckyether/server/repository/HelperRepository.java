package org.luckyether.server.repository;

import org.luckyether.server.model.Newbie;
import org.luckyether.server.model.TransactionHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Igor Hnes on 9/5/17.
 */
public interface HelperRepository extends JpaRepository<TransactionHelper, Long> {

    /**
     * @return list if {@link Newbie} entity between given id.
     */
    @Query("select s from Newbie s where s.id between ?1 and ?2")
    List<Newbie> getBetweenId(final Long from, final Long to);

}
