package org.luckyether.server.repository;

import org.luckyether.server.model.Jackpot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Hnes on 10/29/17.
 */
public interface JackpotRepository extends JpaRepository<Jackpot, Long> {

}
