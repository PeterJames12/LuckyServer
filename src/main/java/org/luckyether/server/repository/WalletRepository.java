package org.luckyether.server.repository;

import org.luckyether.server.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Hnes on 8/13/17.
 */
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
