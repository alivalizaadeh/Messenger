package com.av.user.repository;

import com.av.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String username);
    void deleteByPhoneNumber(String phoneNumber);
    @Query(value = "insert into USER.MESSAGES m (m.userId , m.messageId) values (:userId , :messageId)" , nativeQuery = true)
    void saveMessage(@Param("userId") Long userId ,
                     @Param("messageId") String messageId);
}
