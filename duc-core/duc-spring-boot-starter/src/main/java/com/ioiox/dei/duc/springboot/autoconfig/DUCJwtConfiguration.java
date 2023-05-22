package com.ioiox.dei.duc.springboot.autoconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioiox.dei.duc.springboot.jwt.*;
import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.support.PwdEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
public class DUCJwtConfiguration {

    @Autowired
    private DUCJwtSettings jwtSettings;

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Autowired
    private JwtTokenVerifier jwtTokenVerifier;

    @Autowired
    private PwdEncoder pwdEncoder;

    @Autowired
    private JwtAuthSvc jwtAuthSvc;

    @Autowired
    private JwtCoreSvc jwtCoreSvc;

    @Autowired(required = false)
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    @ConditionalOnMissingBean(JwtAuthSvc.class)
    public JwtAuthSvc jwtAuthSvc() {
        return new DefaultDUCJwtAuthSvc();
    }

    @Bean
    @ConditionalOnMissingBean(JwtCoreSvc.class)
    public JwtCoreSvc jwtCoreSvc() {
        return new DefaultDUCJwtCoreSvc(jwtAuthSvc, jwtTokenFactory, jwtTokenVerifier, jwtSettings, pwdEncoder);
    }

    @Bean
    @ConditionalOnMissingBean(JwtTokenFactory.class)
    public JwtTokenFactory jwtTokenFactory() {
        return new JwtTokenFactory(jwtSettings);
    }

    @Bean
    @ConditionalOnMissingBean(JwtTokenExtractor.class)
    public JwtTokenExtractor tokenExtractor() {
        return new DefaultJwtTokenExtractor(jwtSettings);
    }

    @Bean
    @ConditionalOnMissingBean(JwtTokenVerifier.class)
    public JwtTokenVerifier tokenVerifier() {
        return new DefaultJwtTokenVerifier();
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtAuthSvc, jwtSettings);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationFailureHandler.class)
    public JwtAuthFailureHandler jwtAuthFailureHandler() {
        return new JwtAuthFailureHandler(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new DefaultAuthenticationEntryPoint();
    }

    @Bean("jwtTokenProvider")
    @ConditionalOnMissingBean(JwtTokenProvider.class)
    public JwtTokenProvider jwtTokenProvider() {
        return new DefaultJwtTokenProvider(jwtTokenFactory);
    }
}
