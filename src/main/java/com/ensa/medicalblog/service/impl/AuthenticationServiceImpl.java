package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.config.security.JwtService;
import com.ensa.medicalblog.entity.Role;
import com.ensa.medicalblog.entity.TokenEntity;
import com.ensa.medicalblog.entity.TokenType;
import com.ensa.medicalblog.entity.UserEntity;
import com.ensa.medicalblog.graphql.input.AuthInput;
import com.ensa.medicalblog.graphql.input.RegisterInput;
import com.ensa.medicalblog.graphql.model.Auth;
import com.ensa.medicalblog.repository.TokenRepository;
import com.ensa.medicalblog.repository.UserRepository;
import com.ensa.medicalblog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Auth register(RegisterInput registerInput) {
        var user = UserEntity.builder()
                .firstname(registerInput.getFirstname())
                .lastname(registerInput.getLastname())
                .email(registerInput.getEmail())
                .password(passwordEncoder.encode(registerInput.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return Auth.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(UserEntity savedUserEntity, String jwtToken) {
        var token = TokenEntity.builder()
                .userEntity(savedUserEntity)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public Auth authenticate(AuthInput authInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authInput.getEmail(),
                        authInput.getPassword()
                )
        );
        var user = userRepository.findByEmail(authInput.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return Auth.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(UserEntity userEntity) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(userEntity.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
