package org.luckyether.server.service;

import java.util.List;

/**
 * @author Igor Hnes on 8/27/17.
 */
public interface LuckyGamesService {

    /**
     * Send less transaction.
     */
    void newbieTransaction(List<String> addressList);

    /**
     * Send normal transaction.
     */
    void experiencedTransaction(List<String> addressList);

    /**
     * Send hard transaction.
     */
    void professionalTransaction(List<String> addressList);
}
