package org.luckyether.server.service;

import org.luckyether.server.model.Wallet;

/**
 * @author Igor Hnes on 8/13/17.
 */
public interface WalletService {

    /**
     * @return all wallets.
     */
    Wallet getAll();
}
