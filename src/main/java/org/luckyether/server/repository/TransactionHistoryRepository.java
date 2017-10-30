package org.luckyether.server.repository;

import org.luckyether.server.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Igor Hnes on 10/27/17.
 */
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    /**
     * @return count in history for statistic.
     */
    @Query("select count(senderAddress) from TransactionHistory s where s.senderAddress = ?1")
    Long countBySenderAddress(String address);

    /**
     * @return list of history by given address.
     */
    List<TransactionHistory> getAllBySenderAddress(String address);
}
