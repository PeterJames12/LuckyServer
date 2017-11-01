package org.luckyether.server.repository;

import org.luckyether.server.model.OutTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface OutTransactionRepository extends JpaRepository<OutTransaction, Long> {

    /**
     * @return list of statistic by given address.
     */
    List<OutTransaction> getAllByWinnerAddress(String address);

    /**
     * @return list of today's winners.
     */
    @Query("select s from OutTransaction s where s.data between ?1 and ?2")
    List<OutTransaction> getByToday(LocalDateTime today, LocalDateTime yesterday);
}
