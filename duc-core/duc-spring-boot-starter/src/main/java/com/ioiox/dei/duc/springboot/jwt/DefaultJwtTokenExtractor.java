package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenNotExistException;
import org.apache.commons.lang3.StringUtils;


public class DefaultJwtTokenExtractor implements JwtTokenExtractor {

    private final DUCJwtSettings jwtSettings;

    public DefaultJwtTokenExtractor(final DUCJwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public String extractToken(final String rawJwtToken) {
        final String tokenPrefix = StringUtils.isBlank(jwtSettings.getTokenPrefix())
                ? DEFAULT_TOKEN_PREFIX : jwtSettings.getTokenPrefix();
        final String tokenSeparator = StringUtils.isBlank(jwtSettings.getTokenSeparator())
                ? DEFAULT_TOKEN_SEPARATOR : jwtSettings.getTokenSeparator();
        if (StringUtils.isBlank(rawJwtToken)
                || StringUtils.length(rawJwtToken) < StringUtils.length(tokenPrefix)) {
            throw new JwtTokenNotExistException(AuthErr.JWT_TOKEN_NOT_EXIST.getDesc());
        }
        return rawJwtToken.substring(tokenPrefix.length() + tokenSeparator.length());
    }
}
