package com.example.medica.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
public void handle(

HttpServletRequest request,
HttpServletResponse response,
AccessDeniedException accessDeniedException
)throws IOException, ServletException{
log.info("Access Denied, user unauthenticated");
response.sendError(HttpServletResponse.SC_FORBIDDEN,"Forbidden");

} 

}