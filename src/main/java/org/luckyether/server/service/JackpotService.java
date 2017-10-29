package org.luckyether.server.service;

import org.luckyether.server.model.Jackpot;

/**
 * @author Igor Hnes on 9/4/17.
 */
public interface JackpotService {

    /**
     * @return current jackpot in game.
     */
    String getCurrentJackpot();

    /**
     * Save {@link Jackpot} entity.
     */
    void save(Jackpot jackpot);
}
