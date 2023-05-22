package com.ioiox.dei.duc.springboot.jwt;


import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtAccessToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * An {@link org.springframework.security.core.Authentication} implementation
 * that is designed for simple presentation of JwtToken.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private DUCJwtAccessToken jwtAccessToken;
    private final DUCUserPortfolio userPortfolio;

    public JwtAuthenticationToken(final DUCUserPortfolio userPortfolio,
                                  final DUCJwtAccessToken jwtAccessToken) {
        super(null);
        this.userPortfolio = userPortfolio;
        this.jwtAccessToken = jwtAccessToken;
    }

    @Override
    public void setAuthenticated(final boolean authenticated) {
        // Cannot set this token to trusted, so do nothing
    }

    @Override
    public Object getCredentials() {
        return jwtAccessToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userPortfolio;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.jwtAccessToken = null;
    }
}
