package org.luckyether.server.service.impl;

import org.luckyether.server.model.Jackpot;
import org.luckyether.server.repository.JackpotRepository;
import org.luckyether.server.service.JackpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Igor Hnes on 9/4/17.
 */
@Service
public class JackpotServiceImpl implements JackpotService {

    @Autowired
    private JackpotRepository jackpotRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getCurrentJackpot() {
        return "100";
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(Jackpot jackpot) {
        jackpotRepository.save(jackpot);
    }
}
