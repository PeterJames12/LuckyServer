package org.luckyether.server.controller;

import lombok.RequiredArgsConstructor;
import org.luckyether.server.model.TransactionHistory;
import org.luckyether.server.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Igor Hnes on 11/1/17.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * @return list of statistic for user by given address.
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<TransactionHistory> getHistory(@RequestParam String address) {
        return historyService.getHistoryByAddress(address);
    }
}
