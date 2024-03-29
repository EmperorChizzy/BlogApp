package com.example.blog.repository;
import com.example.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

        Optional<Users> findByUsername(String username);
        boolean existsByUsername(String username);
    }
