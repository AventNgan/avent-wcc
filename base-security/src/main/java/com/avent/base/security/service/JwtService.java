package com.avent.base.security.service;

import org.springframework.beans.factory.annotation.Value;

public interface JwtService {

    String createJwt(String username, String jwtSecret);
}
