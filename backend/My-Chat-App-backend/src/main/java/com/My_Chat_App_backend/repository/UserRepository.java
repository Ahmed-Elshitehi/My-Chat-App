package com.My_Chat_App_backend.repository;

import com.My_Chat_App_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    List<User> findByUsernameContainingIgnoreCase(String usernamePart);
    List<User> findByEmailContainingIgnoreCase(String emailPart);
    boolean existsByEmail(String email);
}
