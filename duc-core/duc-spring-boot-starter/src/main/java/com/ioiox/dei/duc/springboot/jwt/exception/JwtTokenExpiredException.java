package com.ioiox.dei.duc.springboot.jwt.exception;

import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;

public class JwtTokenExpiredException extends JwtTokenException {

    public JwtTokenExpiredException(final String msg) {
        this(null, msg);
    }

    public JwtTokenExpiredException(final JwtToken token, final String msg) {
        super(token, msg);
    }

    public JwtTokenExpiredException(final JwtToken token, final String msg, final Throwable t) {
        super(token, msg, t);
    }
}
