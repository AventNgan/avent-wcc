package com.avent.base.security.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.avent.base.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    public static final long TOKEN_VALIDITY_MILIS = 1000 * 60 * 60 * 1; // 1 hour


    @Override
    public String createJwt(String username, String jwtSecret){
        String jwtToken = JWT.create()
                .withIssuer("Avent-WCC")
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_MILIS))
                .withJWTId(UUID.randomUUID()
                        .toString())
                .sign(Algorithm.HMAC256(jwtSecret));

        return jwtToken;
    }
}
