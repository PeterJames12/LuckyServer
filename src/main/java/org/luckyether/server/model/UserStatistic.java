package org.luckyether.server.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Igor Hnes on 10/30/17.
 */
@Getter
@Setter
public class UserStatistic {

    private Long wins;
    private Long totalGames;
    private String totalWinsEther;
}
