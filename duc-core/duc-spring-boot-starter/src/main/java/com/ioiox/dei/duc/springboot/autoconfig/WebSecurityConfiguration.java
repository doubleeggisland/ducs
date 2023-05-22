package com.ioiox.dei.duc.springboot.autoconfig;

import com.ioiox.dei.duc.springboot.jwt.JwtAuthenticationProvider;
import com.ioiox.dei.duc.springboot.jwt.JwtTokenAuthenticationProcessingFilter;
import com.ioiox.dei.duc.springboot.jwt.JwtTokenExtractor;
import com.ioiox.dei.duc.springboot.jwt.SkipPathRequestMatcher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private DUCSysSettings sysSettings;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;
    @Autowired
    private AuthenticationManager authenticationManager;

    protected JwtTokenAuthenticationProcessingFilter jwtTokenAuthenticationProcessingFilter(final List<String> pathsToSkip, final String pattern) {
        final SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        final JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(failureHandler, jwtTokenExtractor, matcher);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final List<String> permitAllEndpointList = getSkipUrls();
        http.csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String [] {}))
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(sysSettings.getApiRootPath()).authenticated() // Protected API End-points
                .and();

        if (log.isInfoEnabled()) {
            log.info("配置基于JWT的AuthenticationProcessingFilter ======> jwtTokenAuthenticationProcessingFilter");
        }
        http.addFilterBefore(jwtTokenAuthenticationProcessingFilter(permitAllEndpointList, sysSettings.getApiRootPath()),
                UsernamePasswordAuthenticationFilter.class);
    }


    private List<String> getSkipUrls() {
        List<String> skipUrls = new ArrayList<>();
        skipUrls.add("/console");

        if (StringUtils.isNotBlank(sysSettings.getLoginUrl())) {
            skipUrls.add(sysSettings.getLoginUrl());
        }

        if (StringUtils.isNotBlank(sysSettings.getRenewAccessTokenUrl())) {
            skipUrls.add(sysSettings.getRenewAccessTokenUrl());
        }

        if (Objects.nonNull(sysSettings.getSkipUrls()) && sysSettings.getSkipUrls().length > 0) {
            for (final String urlToSkip : sysSettings.getSkipUrls()) {
                if (StringUtils.isNotBlank(urlToSkip)) {
                    skipUrls.add(urlToSkip);
                }
            }
        }

        return skipUrls;
    }
}
