package com.avent.base.security.service.impl;

import com.avent.base.security.service.UserCredentialService;
import com.avent.security.entity.UserCredentialEntity;
import com.avent.security.dao.UserCredentialDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCredentialServiceImpl implements UserCredentialService {

    private final UserCredentialDao userCredentialDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticateUser(String username, char[] password){
        UserCredentialEntity foundUser = userCredentialDao.findByUsername(username);
        if (foundUser == null) {
            return false;
        }

        return passwordEncoder.matches(CharBuffer.wrap(password), foundUser.getPassword());
    }

    @Override
    public String createUser(String username, char[] password){
        UserCredentialEntity newUser = new UserCredentialEntity();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
        userCredentialDao.save(newUser);
        return newUser.getUsername();
    }

    @Override
    public boolean userExists(String username){
        return userCredentialDao.findByUsername(username) != null;
    }

}
