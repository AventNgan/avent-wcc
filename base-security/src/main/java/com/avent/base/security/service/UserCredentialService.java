package com.avent.base.security.service;

public interface UserCredentialService {
    boolean authenticateUser(String username, char[] password);

    String createUser(String username, char[] password);

    boolean userExists(String username);
}
