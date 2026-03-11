package com.example.medica.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.medica.service.JwtService;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    
private final JwtService jwtService;
private final UserDetailsServiceImpl userDetailsServiceImpl;
private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl, JwtAccessDeniedHandler jwtAccessDeniedHandler){

    this.jwtService = jwtService;
    this.userDetailsServiceImpl = userDetailsServiceImpl;
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;

}


@Override
protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException

{
log.info("JWT FILTER EXECUTADO");

    final String authHeader = request.getHeader("Authorization");
if(authHeader == null|| !authHeader.startsWith("Bearer ")){
filterChain.doFilter(request, response);
return;

}


try{

String token = authHeader.substring(7);
String email = jwtService.extractUsername(token);

if(email !=null && SecurityContextHolder.getContext().getAuthentication() == null  )
{

UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);

if(jwtService.isTokenValid(token, (UserDetailsServiceImpl) userDetails)){


UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()
);

authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)
);
SecurityContextHolder.getContext().setAuthentication(authToken);
}   



log.info("token valido");

}
}

catch(Exception e){

log.info("Token Invalido");
}

filterChain.doFilter(request, response);
}

}

