package com.example.medica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.medica.config.JwtAuthFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityChain {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityChain(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }


    @Bean
public SecurityFilterChain filterChain(HttpSecurity httpSec)throws Exception{
    httpSec
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/register", "/auth/verify-otp","/auth/login").permitAll().anyRequest().authenticated()
            );

return httpSec.build();
}


    @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


}
