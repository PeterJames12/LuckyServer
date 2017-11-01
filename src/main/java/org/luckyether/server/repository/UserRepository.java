package org.luckyether.server.repository;

import org.luckyether.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andre on July 2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @return {@link User} by email    .
     */
    User findByEmail(String email);

    /**
     * @return {@link User} by user Id.
     */
    User findById(Long id);

    @Modifying
    @Query("update User u set u.wallet = ?1 where u.id = ?2")
    void update(String wallet, Long id);
}
