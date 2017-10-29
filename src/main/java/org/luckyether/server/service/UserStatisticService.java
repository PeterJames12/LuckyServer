package org.luckyether.server.service;

import org.luckyether.server.model.UserStatistic;

/**
 * @author Igor Hnes on 10/30/17.
 */
public interface UserStatisticService {

    /**
     * @return statistic for user by given address.
     */
    UserStatistic getStatistic(String address);
}
