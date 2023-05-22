package com.ioiox.dei.duc.springboot.autoconfig;

import com.ioiox.dei.duc.springboot.jwt.JwtCoreSvc;
import com.ioiox.dei.duc.springboot.rest.api.DUCCoreRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class DUCRestApiConfiguration {

    @Autowired
    private JwtCoreSvc jwtCoreSvc;

    @Bean
    public DUCCoreRestApi coreRestApi() {
        return new DUCCoreRestApi(jwtCoreSvc);
    }
}
