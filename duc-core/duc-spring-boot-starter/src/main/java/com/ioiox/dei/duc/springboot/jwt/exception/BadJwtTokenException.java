package com.ioiox.dei.duc.springboot.jwt.exception;

import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;

public class BadJwtTokenException extends JwtTokenException {

    public BadJwtTokenException(final JwtToken token, final String msg) {
        super(token, msg);
    }

    public BadJwtTokenException(final JwtToken token, final String msg, final Throwable t) {
        super(token, msg, t);
    }
}
