package com.avent.base.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.avent.base.model.response.ResponseModel;
import com.avent.base.security.service.JwtService;
import com.avent.base.security.service.UserCredentialService;
import com.avent.security.model.LoginModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final UserCredentialService userCredentialService;
    private final JwtService jwtService;

    @Value("${security.jwt.secret}")
    private String jwtSecret;


    @PostMapping("/login")
    public ResponseModel<String> authenticateUser(@RequestBody LoginModel loginModel) throws Exception {

        Authentication authentication = configuration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                loginModel.getUsername(), loginModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseModel.success(jwtService.createJwt(loginModel.getUsername(), jwtSecret));
    }

    //Temp API for testing
    @PostMapping("/signup")
    public ResponseModel<String> signupUser(@RequestBody LoginModel loginModel) throws Exception {
        String user = userCredentialService.createUser(loginModel.getUsername(), loginModel.getPassword());
        return ResponseModel.success(String.format("User %s signed up successfully", user));
    }
}
