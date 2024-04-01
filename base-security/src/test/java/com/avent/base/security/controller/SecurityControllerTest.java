package com.avent.base.security.controller;

import com.avent.base.model.response.ResponseModel;
import com.avent.base.security.service.JwtService;
import com.avent.base.security.service.UserCredentialService;
import com.avent.security.model.LoginModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SecurityControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthenticationConfiguration configuration;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserCredentialService userCredentialService;

    @InjectMocks
    private SecurityController securityController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldAuthenticateUserSuccessfully() throws Exception {
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("testUser");
        loginModel.setPassword("testPassword".toCharArray());

        Authentication auth = new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword());

        when(configuration.getAuthenticationManager()).thenReturn(authenticationManager);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.createJwt(any(), any())).thenReturn("testToken");

        ResponseModel<String> response = securityController.authenticateUser(loginModel);

        assertEquals("testToken", response.getData());
    }

    @Test
    public void shouldSignupUserSuccessfully() throws Exception {
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("testUser");
        loginModel.setPassword("testPassword".toCharArray());

        when(userCredentialService.createUser(any(), any())).thenReturn("testUser");

        ResponseModel<String> response = securityController.signupUser(loginModel);

        assertEquals(String.format("User %s signed up successfully", loginModel.getUsername()), response.getData());
    }
}
