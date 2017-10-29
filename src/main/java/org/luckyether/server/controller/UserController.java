package org.luckyether.server.controller;

import lombok.RequiredArgsConstructor;
import org.luckyether.server.dto.UserDTO;
import org.luckyether.server.exception.BaseException;
import org.luckyether.server.model.UserStatistic;
import org.luckyether.server.service.UserService;
import org.luckyether.server.service.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Sign up for user.
     */
    @PostMapping(value = "/signup")
    public void addUser(@RequestBody UserDTO user) throws BaseException {
        userService.create(user);
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

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public UserStatistic getStatistic(@RequestParam String address) {
        return userStatisticService.getStatistic(address);
    }
}
