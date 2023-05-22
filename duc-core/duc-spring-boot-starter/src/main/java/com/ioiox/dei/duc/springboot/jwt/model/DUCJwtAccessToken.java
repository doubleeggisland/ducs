package com.ioiox.dei.duc.springboot.jwt.model;

import io.jsonwebtoken.Claims;

public class DUCJwtAccessToken implements JwtToken {

    private final DUCJwtToken jwtToken;

    public DUCJwtAccessToken(final DUCJwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Claims parse(final String signingKey) {
        return jwtToken.parse(signingKey);
    }

    @Override
    public String getRawToken() {
        return jwtToken.getRawToken();
    }
}
