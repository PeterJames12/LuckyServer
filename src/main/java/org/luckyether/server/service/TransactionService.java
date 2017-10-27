package org.luckyether.server.service;

import org.luckyether.server.util.Ether;
import org.web3j.protocol.exceptions.TransactionTimeoutException;

import java.util.concurrent.ExecutionException;

/**
 * @author Igor Hnes on 9/4/17.
 */
public interface TransactionService {

    /**
     * @param address is winner.
     * @param ether how much winner get.
     */
    void sendTransaction(String address, Ether ether) throws InterruptedException, ExecutionException, TransactionTimeoutException;

    /**
     * Listening ether transaction in different thread.
     */
    void listeningTransaction();
}
