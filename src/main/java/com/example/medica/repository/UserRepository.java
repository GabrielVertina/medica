package com.example.medica.repository;

import com.example.medica.domain.UserRoot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserRoot, UUID> {

    Optional<UserRoot> findByEmail(String email);


}
