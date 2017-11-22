package org.luckyether.server.service.impl;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.luckyether.server.service.LuckyGamesService;
import org.luckyether.server.service.TransactionService;
import org.luckyether.server.util.Ether;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionTimeoutException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author Igor Hnes on 8/27/17.
 */
@Service
public class LuckyGamesServiceImpl implements LuckyGamesService {

    private static final int RANDOM_VALUE = 100;

    @Autowired
    private TransactionService transactionService;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void newbieTransaction(List<String> users) {
        try {
            final String winner = findWinner(users);
            transactionService.sendTransaction(winner, Ether.NEWBIE);
        } catch (IOException | InterruptedException | TransactionTimeoutException | ExecutionException | CipherException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void experiencedTransaction(List<String> users) {
        try {
            final String winner = findWinner(users);
            transactionService.sendTransaction(winner, Ether.EXPERIENCED);
        } catch (IOException | InterruptedException | TransactionTimeoutException | ExecutionException | CipherException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void professionalTransaction(List<String> users) {
        try {
            final String winner = findWinner(users);
            transactionService.sendTransaction(winner, Ether.PROFESSIONAL);
        } catch (IOException | InterruptedException | TransactionTimeoutException | ExecutionException | CipherException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get winner in cantrip from address list.
     */
    private synchronized String findWinner(List<String> users) throws IOException {
//        final int numberParticipants = users.size();
//        final int randomNumber = getRandomNumber();
//        final BigDecimal etherCourse = findEtherUsd();
//        final BigDecimal exchangeRatePairEthBtc = findEtherBtc();
//        final int winnerIndex = numberParticipants * ((randomNumber * etherCourse.intValue())
//                / exchangeRatePairEthBtc.intValue()) / RANDOM_VALUE;
//        return users.get(winnerIndex);
        return users.get(new Random().nextInt(users.size()));
    }

    /**
     * @return instance of market service.
     */
    private MarketDataService marketService() {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
        return exchange.getMarketDataService();
    }

    /**
     * @return random value from 0 to 100.
     */
    private int getRandomNumber() {
        final Random random = new Random();
        return random.nextInt(RANDOM_VALUE);
    }

    /**
     * @return exchange rate eth to usd.
     */
    private BigDecimal findEtherUsd() throws IOException {
        return marketService().getTicker(CurrencyPair.ETH_USD).getLast();
    }

    /**
     * @return exchange rate eth to btc.
     */
    private BigDecimal findEtherBtc() throws IOException {
        return marketService().getTicker(CurrencyPair.ETH_BTC).getLast();
    }
}
