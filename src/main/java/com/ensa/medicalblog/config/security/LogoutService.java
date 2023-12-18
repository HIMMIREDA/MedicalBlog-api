package com.ensa.medicalblog.config.security;

import com.ensa.medicalblog.graphql.model.Logout;
import com.ensa.medicalblog.repository.TokenRepository;
import io.leangen.graphql.spqr.spring.autoconfigure.DefaultGlobalContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final TokenRepository tokenRepository;

    public Logout logout(DefaultGlobalContext<ServletWebRequest> context) {
        ServletWebRequest request = context.getNativeRequest();
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return Logout.builder()
                    .message("No Authorization header !")
                    .build();
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }

        return Logout.builder()
                .message("Logout succeeded.")
                .build();
    }
}
