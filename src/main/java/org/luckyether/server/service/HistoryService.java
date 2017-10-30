package org.luckyether.server.service;

import org.luckyether.server.model.TransactionHistory;

import java.util.List;

/**
 * @author Igor Hnes on 10/30/17.
 */
public interface HistoryService {

    /**
     * Save {@link TransactionHistory} entity.
     */
    void save(TransactionHistory transactionHistory);

    /**
     * @return count in history for statistic.
     */
    Long countBySenderAddress(String address);

    /**
     * @return list of history by given address.
     */
    List<TransactionHistory> getHistoryByAddress(String address);
}
