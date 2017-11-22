package org.luckyether.server.service.impl;

import org.luckyether.server.model.Jackpot;
import org.luckyether.server.model.JackpotSum;
import org.luckyether.server.repository.JackpotRepository;
import org.luckyether.server.repository.JackpotSumRepository;
import org.luckyether.server.service.JackpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Igor Hnes on 9/4/17.
 */
@Service
public class JackpotServiceImpl implements JackpotService {

    @Autowired
    private JackpotRepository jackpotRepository;

    @Autowired
    private JackpotSumRepository jackpotSumRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getCurrentJackpot() {
        final List<JackpotSum> jackpotSums = jackpotSumRepository.findAll();
        BigDecimal currentJackpot = new BigDecimal("0");
        for (JackpotSum element : jackpotSums) {
            currentJackpot = currentJackpot.add(new BigDecimal(element.getEther()));
        }
        return currentJackpot.toString();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(Jackpot jackpot) {
        jackpotRepository.save(jackpot);
    }
}
