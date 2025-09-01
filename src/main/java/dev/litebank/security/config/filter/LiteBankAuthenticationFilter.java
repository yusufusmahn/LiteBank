package dev.litebank.security.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.litebank.security.dto.request.AuthRequest;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class LiteBankAuthenticationFilter extends OncePerRequestFilter {


    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper  mapper = new ObjectMapper();
        InputStream requestBody = request.getInputStream(); //{"username":"", "password":""} what we have here is json
        byte[] body = requestBody.readAllBytes();
        AuthRequest authRequest = mapper.readValue(requestBody, AuthRequest.class);
        String username = authRequest.getPassword();
        String password = authRequest.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authenticationManager.authenticate(authentication);
    }
}
