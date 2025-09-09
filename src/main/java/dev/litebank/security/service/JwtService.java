package dev.litebank.security.service;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateAccessToken(Authentication authentication);
    String generateRefreshToken(Authentication authentication);
    boolean isJwtTokenValid(String token);
    Claim extractClaim(String token, String claimName);
}
