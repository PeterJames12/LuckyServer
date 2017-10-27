package org.luckyether.server.service.impl;

import org.luckyether.server.service.JackpotService;
import org.springframework.stereotype.Service;

/**
 * @author Igor Hnes on 9/4/17.
 */
@Service
public class JackpotServiceImpl implements JackpotService {

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getCurrentJackpot() {
        return "100";
    }
}
