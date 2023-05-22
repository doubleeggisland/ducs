package com.ioiox.dei.duc.springboot.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException ex) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), AuthErr.AUTHENTICATION.getDesc());
    }
}
