package org.luckyether.server.controller;

import lombok.RequiredArgsConstructor;
import org.luckyether.server.dto.UserDTO;
import org.luckyether.server.exception.BaseException;
import org.luckyether.server.model.TransactionHistory;
import org.luckyether.server.model.UserStatistic;
import org.luckyether.server.service.HistoryService;
import org.luckyether.server.service.UserService;
import org.luckyether.server.service.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Andre on July 2017.
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatisticService userStatisticService;

    @Autowired
    private HistoryService historyService;

    /**
     * Sign up for user.
     */
    @PostMapping(value = "/signup")
    public UserDTO addUser(@RequestBody UserDTO user) throws BaseException {
        return userService.create(user);
    }

    /**
     * Update user.
     */
    @PutMapping(value = "/update")
    public void update(@RequestBody UserDTO user) throws BaseException {
        userService.update(user);
    }

    /**
     * @return instance of {@link UserDTO}.
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserDTO getUser(@RequestParam String email) {
        return userService.getUserByUserEmail(email);
    }

    /**
     * @param email is user's email on which will send message for recover password.
     */
    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public void passwordRecovery(@RequestParam String email) {
        userService.changePassword(email);
    }

    /**
     * @return statistic for user by given address.
     */
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public UserStatistic getStatistic(@RequestParam String address) {
        return userStatisticService.getStatistic(address);
    }

    /**
     * @return list of statistic for user by given address.
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<TransactionHistory> getHistory(@RequestParam String address) {
        return historyService.getHistoryByAddress(address);
    }
}
