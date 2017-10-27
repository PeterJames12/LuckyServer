package org.luckyether.server.controller;

import org.luckyether.server.exception.BaseException;
import org.luckyether.server.model.Verification;
import org.luckyether.server.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Hnes on 8/12/17.
 */
@RestController
@RequestMapping(value = "verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @RequestMapping(value = "/verificate", method = RequestMethod.POST)
    public void validateUserByCode(@RequestParam String email) throws BaseException {
        verificationService.verificateUserByCode(email);
    }

    @RequestMapping(value = "/after", method = RequestMethod.POST)
    public String validateUserAfter(@RequestBody Verification verification) throws BaseException {
        return verificationService.verificateUserAfter(verification);
    }
}
