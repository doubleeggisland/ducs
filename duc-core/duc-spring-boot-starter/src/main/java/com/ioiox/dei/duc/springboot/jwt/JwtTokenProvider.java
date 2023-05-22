package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.spring.core.model.DUCUserType;

public interface JwtTokenProvider {

    String generateJwtRawAccessToken(final String username, final DUCUserType userType);
}
