package com.trantor.authserver.repository;

import com.trantor.authserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT user FROM User user WHERE user.username = :username and user.enable = true")
    User findByUsername(@Param("username") String username);
}
