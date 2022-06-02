package com.trantor.trantorapp.repository;

import com.trantor.trantorapp.domain.Message;
import com.trantor.trantorapp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MessageRepository
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByFrom(User user, Pageable pageable);
}
