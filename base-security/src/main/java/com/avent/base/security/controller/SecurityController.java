package com.avent.base.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.avent.base.model.response.ResponseModel;
import com.avent.security.model.LoginModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SecurityController {

    private final AuthenticationConfiguration configuration;
    private final Algorithm algorithm;

    public static final long TOKEN_VALIDITY_MILIS = 1000 * 60 * 60 * 1; // 1 hour

    @PostMapping("/login")
    public ResponseModel<String> authenticateUser(@RequestBody LoginModel loginModel) throws Exception {

        Authentication authentication = configuration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                loginModel.getUsername(), loginModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = JWT.create()
                .withIssuer("Avent-WCC")
                .withSubject(loginModel.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MILIS))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(algorithm);

        return ResponseModel.success(jwtToken);
    }
}
