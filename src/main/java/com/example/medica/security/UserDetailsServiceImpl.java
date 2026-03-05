package com.example.medica.security;

import com.example.medica.domain.UserRoot;
import com.example.medica.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl ( UserRepository repository){
        this.repository=repository;
    }

    @Override
public UserDetails loadUserByUsername(String email){
UserRoot userRoot = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found!"));

return org.springframework.security.core.userdetails.User
        .withUsername(userRoot.getEmail())
        .password(userRoot.getPassword())
        .authorities("USER")
        .build();
    }

}
