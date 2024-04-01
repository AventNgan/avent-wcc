package com.avent.base.security.service.impl;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateJwtWithValidUsername() {
        String username = "validUsername";
        String token = jwtService.createJwt(username, "secret");

        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"));
        assertTrue(token.split("\\.").length == 3);
    }

    @Test
    public void shouldCreateJwtWithEmptyUsername() {
        String username = "";
        String token = jwtService.createJwt(username,"secret");

        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"));
        assertTrue(token.split("\\.").length == 3);
    }

    @Test
    public void shouldCreateJwtWithNullUsername() {
        String token = jwtService.createJwt(null, "secret");

        assertNotNull(token);
        assertTrue(token.startsWith("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"));
        assertTrue(token.split("\\.").length == 3);
    }
}
