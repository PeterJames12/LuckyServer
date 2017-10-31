package org.luckyether.server.controller;

import lombok.RequiredArgsConstructor;
import org.luckyether.server.exception.BaseException;
import org.luckyether.server.model.Wallet;
import org.luckyether.server.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Igor Hnes on 8/13/17.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/getWallets", method = RequestMethod.POST)
    public Wallet getAllWallets() throws BaseException {
        return walletService.getAll();
    }
}
