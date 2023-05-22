package com.ioiox.dei.duc.springboot.jwt;

/**
 * Implementations of this interface should always return raw base-64 encoded
 * representation of JWT Token.
 */
public interface JwtTokenExtractor {
    String DEFAULT_TOKEN_PREFIX = "duc";
    String DEFAULT_TOKEN_SEPARATOR = " ";
    String DEFAULT_TOKEN_REQ_HEADER_KEY = "duc-access-token";
    String extractToken(String rawJwtToken);
}
