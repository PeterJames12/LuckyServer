package org.luckyether.server.repository;

import org.luckyether.server.model.OutTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface OutTransactionRepository extends JpaRepository<OutTransaction, Long> {

    /**
     * @return list of statistic by given address.
     */
    List<OutTransaction> getAllByWinnerAddress(String address);
}
