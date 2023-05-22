package com.ioiox.dei.duc.springboot.jwt;


public interface JwtTokenVerifier {
    boolean valid(final String jti);
    boolean invalid(final String jti);
}
