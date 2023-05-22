package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.spring.core.model.DUCUserType;
import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;

public class DefaultJwtTokenProvider implements JwtTokenProvider {

    private final JwtTokenFactory jwtTokenFactory;

    public DefaultJwtTokenProvider(final JwtTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    public String generateJwtRawAccessToken(final String username, final DUCUserType userType) {
        final JwtToken accessToken = jwtTokenFactory.accessToken(username, userType);
        return accessToken.getRawToken();
    }
}
