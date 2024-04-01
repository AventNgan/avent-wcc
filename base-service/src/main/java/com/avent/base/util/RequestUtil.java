package com.avent.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
public class RequestUtil {

    public static final String REQUEST_ID = "REQUEST_ID";

    public static RequestAttributes currentRequestContext() {
        return RequestContextHolder.currentRequestAttributes();
    }

    public static final HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = currentRequestContext();
        if(requestAttributes == null){
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static final Object getAttributes(String key) {
        HttpServletRequest request = getRequest();

        if(request == null) {
            return null;
        }

        return (String) request.getAttribute(key);
    }

    public static final void putData(String key, Object value){
        HttpServletRequest request = getRequest();
        if(request != null){
            request.setAttribute(key, value);
        }
    }

    public static final Object getData(String key){
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getAttribute(key);
    }
}
