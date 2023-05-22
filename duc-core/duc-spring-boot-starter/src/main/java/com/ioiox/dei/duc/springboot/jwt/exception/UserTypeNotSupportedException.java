package com.ioiox.dei.duc.springboot.jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class UserTypeNotSupportedException extends AuthenticationException {

    private final String userType;

    public UserTypeNotSupportedException(final String userType, final String msg) {
        super(msg);
        this.userType = userType;
    }

    public UserTypeNotSupportedException(final String userType, final String msg, final Throwable t) {
        super(msg, t);
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
