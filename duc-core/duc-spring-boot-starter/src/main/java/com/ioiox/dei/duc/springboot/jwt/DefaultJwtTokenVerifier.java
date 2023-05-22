package com.ioiox.dei.duc.springboot.jwt;


public class DefaultJwtTokenVerifier implements JwtTokenVerifier {
    @Override
    public boolean valid(final String jti) {
        return true;
    }
    @Override
    public boolean invalid(final String jti) {
        return !valid(jti);
    }
}
