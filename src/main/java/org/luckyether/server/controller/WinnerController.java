package org.luckyether.server.controller;

import lombok.RequiredArgsConstructor;
import org.luckyether.server.model.OutTransaction;
import org.luckyether.server.service.OutTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Igor Hnes on 11/1/17.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class WinnerController {

    @Autowired
    private OutTransactionService outTransactionService;

    /**
     * @return list of today's winners.
     */
    @RequestMapping(value = "/getWinners", method = RequestMethod.GET)
    public List<OutTransaction> getWinners() {
        return outTransactionService.getAll();
    }
}
