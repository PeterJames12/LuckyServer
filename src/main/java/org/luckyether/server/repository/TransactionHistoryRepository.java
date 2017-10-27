package org.luckyether.server.repository;

import org.luckyether.server.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Hnes on 10/27/17.
 */
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}
