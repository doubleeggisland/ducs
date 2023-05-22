package com.ioiox.dei.duc.springboot.autoconfig;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.springboot.jwt.AuthErr;
import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.jwt.exception.BadJwtTokenException;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenExpiredException;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenNotExistException;
import com.ioiox.dei.duc.springboot.jwt.exception.UserTypeNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
@ConditionalOnBean(DUCMarkerConfiguration.Marker.class)
@Import({ PwdEncoderConfiguration.class, DUCJwtConfiguration.class, WebSecurityConfiguration.class, DUCRestApiConfiguration.class })
public class DUCAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "com.ioiox.dei.duc.jwt")
    public DUCJwtSettings jwtSettings() {
        return new DUCJwtSettings();
    }

    @Bean
    @ConfigurationProperties(prefix = "com.ioiox.dei.duc.sys")
    public DUCSysSettings sysSettings() {
        return new DUCSysSettings();
    }

    @ControllerAdvice
    @ConditionalOnClass(AuthenticationException.class)
    public static class AuthenticationExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

        public AuthenticationExceptionHandler() {
            if (log.isInfoEnabled()) {
                log.info("AuthenticationExceptionHandler is instantiated");
            }
        }

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<DeiResponseData<String>> handlerServiceException(final AuthenticationException e) {
            log.error(e.getMessage(), e);

            final AuthErr errCode;
            if (e instanceof BadCredentialsException) {
                errCode = AuthErr.BAD_CREDENTIAL;
            } else if (e instanceof JwtTokenNotExistException) {
                errCode = AuthErr.JWT_TOKEN_NOT_EXIST;
            } else if (e instanceof JwtTokenExpiredException) {
                errCode = AuthErr.JWT_TOKEN_EXPIRED;
            } else if (e instanceof BadJwtTokenException) {
                errCode = AuthErr.JWT_TOKEN_BAD;
            } else if (e instanceof UsernameNotFoundException) {
                errCode = AuthErr.USER_NOT_FOUND;
            } else if (e instanceof DisabledException) {
                errCode = AuthErr.USER_DISABLED;
            } else if (e instanceof UserTypeNotSupportedException) {
                errCode = AuthErr.USER_TYPE_NOT_SUPPORTED;
            } else {
                errCode = AuthErr.AUTHENTICATION;
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new DeiResponseData.Builder<String>()
                            .code(errCode.getCode())
                            .success(DeiGlobalConstant.FALSE_STR)
                            .msg(e.getMessage())
                            .build());
        }
    }
}
