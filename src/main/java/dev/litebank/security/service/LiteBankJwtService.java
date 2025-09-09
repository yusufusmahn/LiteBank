package dev.litebank.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LiteBankJwtService implements  JwtService {


    @Override
    public String generateJwt(Authentication authentication) {
        String jwt = JWT.create()
                .withIssuer("https://litebank.com")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24))
                .withSubject(username)
                .withClaim("roles", authResult.getAuthorities()
                        .stream().map(a->a.getAuthority()).toList())
                .sign(Algorithm.HMAC256(SigningKey));
    }

    @Override
    public boolean isJwtTokenValid(String token) {
        return false;
    }
}
