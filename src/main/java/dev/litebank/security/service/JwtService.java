package dev.litebank.security.service;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface JwtService {
    String generateJwt(Authentication authentication);

    boolean isJwtTokenValid(String token);
}
