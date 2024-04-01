package com.avent.base.security.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.avent.base.security.filter.JwtTokenFilter;
import com.avent.base.security.service.UserCredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JwtTokenFilterTest {

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @Mock
    private JWTVerifier jwtVerifier;

    @Mock
    private UserCredentialService userCredentialService;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void shouldNotFilterWhenAuthorizationHeaderIsEmpty() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void shouldNotFilterWhenAuthorizationHeaderDoesNotStartWithBearer() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Invalid");
        MockHttpServletResponse response = new MockHttpServletResponse();

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void shouldNotFilterWhenUserDoesNotExist() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtVerifier.verify("token")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("username");
        when(userCredentialService.userExists("username")).thenReturn(false);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void shouldFilterWhenUserExists() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtVerifier.verify("token")).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn("username");
        when(userCredentialService.userExists("username")).thenReturn(true);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertEquals("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
