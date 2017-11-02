package org.luckyether.server.service;

import org.luckyether.server.model.OutTransaction;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface OutTransactionService {

    /**
     * @return saved {@link OutTransaction} entity.
     */
    OutTransaction save(OutTransaction outTransaction);

    /**
     * @return list of statistic by given address.
     */
    List<OutTransaction> getAllByWinnerAddress(String address);

    /**
     * @return list of today's winners.
     */
    List<OutTransaction> getAll();
}
