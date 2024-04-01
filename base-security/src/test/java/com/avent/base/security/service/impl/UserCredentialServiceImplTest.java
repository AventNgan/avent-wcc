package com.avent.base.security.service.impl;

import com.avent.base.security.service.impl.UserCredentialServiceImpl;
import com.avent.security.dao.UserCredentialDao;
import com.avent.security.entity.UserCredentialEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserCredentialServiceImplTest {

    @InjectMocks
    private UserCredentialServiceImpl userCredentialService;

    @Mock
    private UserCredentialDao userCredentialDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldAuthenticateUserWhenCredentialsAreValid() {
        String username = "validUsername";
        char[] password = "validPassword".toCharArray();
        UserCredentialEntity user = new UserCredentialEntity();
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(userCredentialDao.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.matches(any(), eq(user.getPassword()))).thenReturn(true);

        assertTrue(userCredentialService.authenticateUser(username, password));
    }

    @Test
    public void shouldNotAuthenticateUserWhenCredentialsAreInvalid() {
        String username = "invalidUsername";
        char[] password = "invalidPassword".toCharArray();

        when(userCredentialDao.findByUsername(username)).thenReturn(null);

        assertFalse(userCredentialService.authenticateUser(username, password));
    }

    @Test
    public void shouldCreateUserWhenUsernameIsUnique() {
        String username = "uniqueUsername";
        char[] password = "validPassword".toCharArray();
        UserCredentialEntity user = new UserCredentialEntity();
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(userCredentialDao.findByUsername(username)).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn(user.getPassword());
        when(userCredentialDao.save(any())).thenReturn(user);

        assertEquals(username, userCredentialService.createUser(username, password));
    }

    @Test
    public void shouldCheckUserExistenceWhenUserExists() {
        String username = "existingUsername";

        when(userCredentialDao.findByUsername(username)).thenReturn(new UserCredentialEntity());

        assertTrue(userCredentialService.userExists(username));
    }

    @Test
    public void shouldCheckUserExistenceWhenUserDoesNotExist() {
        String username = "nonExistingUsername";

        when(userCredentialDao.findByUsername(username)).thenReturn(null);

        assertFalse(userCredentialService.userExists(username));
    }
}
