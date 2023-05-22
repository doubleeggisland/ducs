package com.ioiox.dei.duc.springboot.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenNotExistException extends AuthenticationException {

    public JwtTokenNotExistException(final String msg) {
        super(msg);
    }

    public JwtTokenNotExistException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
