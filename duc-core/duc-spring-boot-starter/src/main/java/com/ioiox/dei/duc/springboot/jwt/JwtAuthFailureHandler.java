package com.ioiox.dei.duc.springboot.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.springboot.jwt.exception.BadJwtTokenException;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenExpiredException;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenNotExistException;
import com.ioiox.dei.duc.springboot.jwt.exception.UserTypeNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthFailureHandler implements AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFailureHandler.class);
    private final ObjectMapper mapper;

    public JwtAuthFailureHandler(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final AuthenticationException e) throws IOException {
        log.error(e.getMessage(), e);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final AuthErr errCode;
        if (e instanceof BadCredentialsException) {
            errCode = AuthErr.BAD_CREDENTIAL;
        } else if (e instanceof JwtTokenExpiredException) {
            errCode = AuthErr.JWT_TOKEN_EXPIRED;
        } else if (e instanceof BadJwtTokenException) {
            errCode = AuthErr.JWT_TOKEN_BAD;
        } else if (e instanceof UsernameNotFoundException) {
            errCode = AuthErr.USER_NOT_FOUND;
        } else if (e instanceof JwtTokenNotExistException) {
            errCode = AuthErr.JWT_TOKEN_NOT_EXIST;
        } else if (e instanceof DisabledException) {
            errCode = AuthErr.USER_DISABLED;
        } else if (e instanceof UserTypeNotSupportedException) {
            errCode = AuthErr.USER_TYPE_NOT_SUPPORTED;
        } else {
            errCode = AuthErr.AUTHENTICATION;
        }

        mapper.writeValue(response.getWriter(),
                new DeiResponseData.Builder<>()
                        .code(errCode.getCode())
                        .success(DeiGlobalConstant.TRUE_STR)
                        .msg(e.getMessage())
                        .build());
    }
}
