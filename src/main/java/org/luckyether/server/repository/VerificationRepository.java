package org.luckyether.server.repository;

import org.luckyether.server.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Igor Hnes on 8/12/17.
 */
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    /**
     * @return {@link Verification} by email    .
     */
    Verification findByEmail(String email);
}
