package com.example.medica.config;

import com.example.medica.security.JwtAccessDeniedHandler;
import com.example.medica.security.JwtAuthFilter;
import com.example.medica.security.UserDetailsServiceImpl;
import com.example.medica.security.JwtAuthEntryPoint;

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
private final JwtAuthEntryPoint jwtAuthEntryPoint;
private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


 public SecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter,JwtAuthEntryPoint jwtAuthEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler ){
      this.userDetailsService = userDetailsService;
 this.jwtAuthFilter = jwtAuthFilter;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    this.jwtAuthEntryPoint = jwtAuthEntryPoint;
}

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

http.csrf(csrf -> csrf.disable()
)
.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint)
.accessDeniedHandler(jwtAccessDeniedHandler)
)
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    )
        .userDetailsService(userDetailsService)
        .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
 return http.build();

 }

@Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();

}



}
