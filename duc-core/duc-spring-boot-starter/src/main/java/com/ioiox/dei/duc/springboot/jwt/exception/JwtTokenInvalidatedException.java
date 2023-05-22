package com.ioiox.dei.duc.springboot.jwt.exception;

import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;

public class JwtTokenInvalidatedException extends JwtTokenException {

    public JwtTokenInvalidatedException(final JwtToken token, final String msg) {
        super(token, msg);
    }
}
