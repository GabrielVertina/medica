package com.example.medica.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.medica.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
private final JwtService jwtService;
private final UserDetailsServiceImpl userDetailsServiceImpl;


public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl){

    this.jwtService = jwtService;
    this.userDetailsServiceImpl = userDetailsServiceImpl;

}


@Override
protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException

{

final String authHeader = request.getHeader("Authorized");

if(authHeader == null|| !authHeader.startsWith("Bearer ")){
filterChain.doFilter(request, response);
return;

}

String token = authHeader.substring(7);
String email = jwtService.extractUsername(token);

if(email !=null && SecurityContextHolder.getContext().getAuthentication() == null  )
{

UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

}

filterChain.doFilter(request, response);




}






}
