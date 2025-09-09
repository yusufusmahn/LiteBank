package dev.litebank.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LiteBankJwtService implements  JwtService {


    @Override
    public String generateAccessToken(Authentication authentication) {
        return "";
    }

    @Override
    public String generateRefreshToken(Authentication authentication) {
        return "";
    }

    @Override
    public boolean isJwtTokenValid(String token) {
        return false;
    }

    @Override
    public Claim extractClaim(String token, String claimName) {
        return null;
    }
}
