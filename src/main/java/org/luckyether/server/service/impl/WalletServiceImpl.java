package org.luckyether.server.service.impl;

import org.luckyether.server.model.Wallet;
import org.luckyether.server.repository.WalletRepository;
import org.luckyether.server.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Igor Hnes on 8/13/17.
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    private static final Long WALLET_ID = 1L;

    /**
     * {@inheritDoc}.
     */
    @Override
    public Wallet getAll() {
        return walletRepository.findOne(WALLET_ID);
    }
}
