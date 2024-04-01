package com.avent.base.security.provider;

import com.avent.base.security.service.UserCredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomAuthenticationProviderTest {

    @InjectMocks
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Mock
    private UserCredentialService userCredentialService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenAuthenticationIsNull() {
        assertThrows(InsufficientAuthenticationException.class, () -> customAuthenticationProvider.authenticate(null));
    }

    @Test
    public void shouldThrowExceptionWhenCredentialsAreInvalid() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("username", "password".toCharArray());
        when(userCredentialService.authenticateUser("username", "password".toCharArray())).thenReturn(false);

        assertThrows(InsufficientAuthenticationException.class, () -> customAuthenticationProvider.authenticate(authentication));
    }

    @Test
    public void shouldReturnAuthenticationWhenCredentialsAreValid() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("username", "password".toCharArray());
        when(userCredentialService.authenticateUser("username", "password".toCharArray())).thenReturn(true);

        Authentication result = customAuthenticationProvider.authenticate(authentication);

        assertEquals("username", result.getName());
        assertEquals("", result.getCredentials());
    }

    @Test
    public void shouldSupportUsernamePasswordAuthenticationToken() {
        assertTrue(customAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void shouldNotSupportOtherAuthenticationTypes() {
        assertFalse(customAuthenticationProvider.supports(CustomAuthenticationProvider.class));
    }
}
