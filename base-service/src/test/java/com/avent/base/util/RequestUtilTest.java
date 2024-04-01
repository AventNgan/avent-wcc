package com.avent.base.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestUtilTest {

    private MockHttpServletRequest request;

    @BeforeEach
    public void setup() {
        request = new MockHttpServletRequest();
        MockitoAnnotations.openMocks(this);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void shouldReturnCurrentRequestContext() {
        assertEquals(RequestContextHolder.currentRequestAttributes(), RequestUtil.currentRequestContext());
    }

    @Test
    public void shouldReturnRequest() {
        assertEquals(request, RequestUtil.getRequest());
    }

    @Test
    public void shouldReturnAttributes() {
        String key = "key";
        String value = "value";
        request.setAttribute(key, value);

        assertEquals(value, RequestUtil.getAttributes(key));
    }

    @Test
    public void shouldReturnNullWhenAttributeNotFound() {
        String key = "nonExistingKey";

        assertNull(RequestUtil.getAttributes(key));
    }

    @Test
    public void shouldPutData() {
        String key = "key";
        String value = "value";

        RequestUtil.putData(key, value);
        request.setAttribute("Test", "Test");

        assertEquals(value, request.getAttribute(key));
    }

    @Test
    public void shouldGetData() {
        String key = "key";
        String value = "value";
        request.setAttribute(key, value);

        assertEquals(value, RequestUtil.getData(key));
    }

    @Test
    public void shouldReturnNullWhenDataNotFound() {
        String key = "nonExistingKey";

        assertNull(RequestUtil.getData(key));
    }
}
