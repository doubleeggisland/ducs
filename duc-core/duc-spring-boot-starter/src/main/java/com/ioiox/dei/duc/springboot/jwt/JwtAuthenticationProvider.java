package com.ioiox.dei.duc.springboot.jwt;


import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import com.ioiox.dei.duc.spring.core.model.DUCUserType;
import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.jwt.exception.UserTypeNotSupportedException;
import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtAccessToken;
import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtAuthSvc jwtAuthSvc;
    private final DUCJwtSettings jwtSettings;

    public JwtAuthenticationProvider(final JwtAuthSvc jwtAuthSvc,
                                     final DUCJwtSettings jwtSettings) {
        this.jwtAuthSvc = jwtAuthSvc;
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final DUCJwtAccessToken jwtAccessToken = (DUCJwtAccessToken) authentication.getCredentials();
        final Claims claims = jwtAccessToken.parse(jwtSettings.getAccessTokenSigningKey());
        final String userType = claims.get(JwtTokenFactory.CLAIMS_KEY_USER_TYPE, String.class);
        final DUCUserType validUserType = StringUtils.isBlank(userType) ? null : DUCUserType.getByCode(userType);

        if (Objects.isNull(validUserType)) {
            throw new UserTypeNotSupportedException(userType, String.format("%s: %s", AuthErr.USER_TYPE_NOT_SUPPORTED.getDesc(), userType));
        }
        final String username = claims.getSubject();
        final DUCUserPortfolio userPortfolio = jwtAuthSvc.getUserPortfolio(username, validUserType);
        if (Objects.isNull(userPortfolio)) {
            throw new UsernameNotFoundException(String.format("%s: userType: %s, username: %s",
                    AuthErr.USER_NOT_FOUND.getDesc(), validUserType.getCode(), username));
        }

        return new JwtAuthenticationToken(userPortfolio, jwtAccessToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
