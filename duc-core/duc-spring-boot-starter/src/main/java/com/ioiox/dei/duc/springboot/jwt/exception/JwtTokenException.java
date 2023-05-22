package com.ioiox.dei.duc.springboot.jwt.exception;

import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;
import org.springframework.security.core.AuthenticationException;

public abstract class JwtTokenException extends AuthenticationException {

    private final JwtToken token;

    public JwtTokenException(final JwtToken token, final String msg) {
        super(msg);
        this.token = token;
    }

    public JwtTokenException(final JwtToken token, final String msg, final Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String rawToken() {
        return this.token.getRawToken();
    }
}
