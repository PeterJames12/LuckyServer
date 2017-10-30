package org.luckyether.server.service.impl;

import lombok.val;
import org.luckyether.server.model.OutTransaction;
import org.luckyether.server.model.UserStatistic;
import org.luckyether.server.service.HistoryService;
import org.luckyether.server.service.OutTransactionService;
import org.luckyether.server.service.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Igor Hnes on 10/30/17.
 */
@Service
public class UserStatisticServiceImpl implements UserStatisticService {

    @Autowired
    private OutTransactionService outTransactionService;

    @Autowired
    private HistoryService historyService;

    /**
     * {@inheritDoc}.
     */
    @Override
    public UserStatistic getStatistic(String address) {
        val outTransactionStatisticList = outTransactionService.getAllByWinnerAddress(address);
        final Long count = historyService.countBySenderAddress(address);
        final UserStatistic userStatistic = new UserStatistic();
        userStatistic.setTotalGames(count);
        userStatistic.setWins((long) outTransactionStatisticList.size());
        double ether = 0.0;
        for (OutTransaction elem : outTransactionStatisticList) {
            final double eth = Double.parseDouble(elem.getEther());
            ether += eth;
        }
        userStatistic.setTotalWinsEther(BigDecimal.valueOf(ether).toString());
        return userStatistic;
    }
}
