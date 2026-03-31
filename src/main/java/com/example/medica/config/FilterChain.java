package com.example.medica.config;
//Aqui sera criada proteção das rotas.


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class FilterChain {




@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

//CORS 


}
