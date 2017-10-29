package org.luckyether.server.service.impl;

import org.luckyether.server.model.OutTransaction;
import org.luckyether.server.repository.OutTransactionRepository;
import org.luckyether.server.service.OutTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
@Service
public class OutTransactionServiceImpl implements OutTransactionService {

    @Autowired
    private OutTransactionRepository outTransactionRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public OutTransaction save(OutTransaction outTransaction) {
        return outTransactionRepository.save(outTransaction);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<OutTransaction> getAllByWinnerAddress(String address) {
        return outTransactionRepository.getAllByWinnerAddress(address);
    }
}
