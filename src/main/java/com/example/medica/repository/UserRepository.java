package com.example.medica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medica.model.UserRoot;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserRoot, UUID> {

    Optional<UserRoot> findByEmail(String email);

boolean existsByEmail(String email);

}
