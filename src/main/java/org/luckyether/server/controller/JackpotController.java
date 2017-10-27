package org.luckyether.server.controller;

import org.luckyether.server.exception.BaseException;
import org.luckyether.server.service.JackpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Igor Hnes on 10/27/17.
 */
@RestController
@RequestMapping
public class JackpotController {

    @Autowired
    private JackpotService jackpotService;

    @RequestMapping(value = "/getCurrentJackpot", method = RequestMethod.GET)
    public String getCurrentJackpot() throws BaseException {
        return jackpotService.getCurrentJackpot();
    }
}
