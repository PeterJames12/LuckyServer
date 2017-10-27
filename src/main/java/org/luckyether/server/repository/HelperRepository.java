package org.luckyether.server.repository;

import org.luckyether.server.model.TransactionHelper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Hnes on 9/5/17.
 */
public interface HelperRepository extends JpaRepository<TransactionHelper, Long> {
}
