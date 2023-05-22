package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtAccessToken;
import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Performs validation of provided JWT Token.
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final JwtTokenExtractor jwtTokenExtractor;
    private final AuthenticationFailureHandler failureHandler;

    @Autowired
    public JwtTokenAuthenticationProcessingFilter(final AuthenticationFailureHandler failureHandler,
                                                  final JwtTokenExtractor jwtTokenExtractor,
                                                  final RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.jwtTokenExtractor = jwtTokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {
        final String rawJwtToken = request.getHeader(JwtTokenExtractor.DEFAULT_TOKEN_REQ_HEADER_KEY);
        final DUCJwtAccessToken jwtAccessToken = new DUCJwtAccessToken(new DUCJwtToken(jwtTokenExtractor.extractToken(rawJwtToken)));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(null, jwtAccessToken));
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final FilterChain chain,
                                            final Authentication authResult) throws IOException, ServletException {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(final HttpServletRequest request,
                                              final HttpServletResponse response,
                                              final AuthenticationException ex) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, ex);
    }
}
