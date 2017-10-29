package org.luckyether.server.service.impl;

import org.luckyether.server.model.TransactionHistory;
import org.luckyether.server.repository.TransactionHistoryRepository;
import org.luckyether.server.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Igor Hnes on 10/30/17.
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private TransactionHistoryRepository historyRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(TransactionHistory transactionHistory) {
        historyRepository.save(transactionHistory);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Long countBySenderAddress(String address) {
        return historyRepository.countBySenderAddress(address);
    }
}
