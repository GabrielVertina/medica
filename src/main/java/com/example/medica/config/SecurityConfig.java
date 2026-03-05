package com.example.medica.config;

import com.example.medica.security.JwtAuthFilter;
import com.example.medica.security.UserDetailsServiceImpl;

import java.security.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity

public class SecurityConfig {

private final UserDetailsServiceImpl userDetailsService;
private final JwtAuthFilter jwtAuthFilter;
 public SecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter){
      this.userDetailsService = userDetailsService;
 this.jwtAuthFilter = jwtAuthFilter;
    }

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
        .userDetailsService(userDetailsService)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
 return http.build();

 }

@Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();

}









}
