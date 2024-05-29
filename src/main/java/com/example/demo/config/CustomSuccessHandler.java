package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Objects;

public class CustomSuccessHandler  implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseEntity<String> entity = ResponseEntity.status(HttpStatus.OK).body("SUCCEED");
        // 클라이언트에게 응답을 보냄
        response.getWriter().write(Objects.requireNonNull(entity.getBody()));
        response.setStatus(entity.getStatusCodeValue());
        response.setContentType("application/json");
    }
}
