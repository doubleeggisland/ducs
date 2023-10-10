package com.ioiox.dei.rest.api.clients.autoconfig;


import com.ioiox.dei.rest.api.clients.DefaultDucsRestApiClient;
import com.ioiox.dei.rest.api.clients.DucsRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({ DeiRestTemplateConfiguration.class })
@EnableConfigurationProperties({ DeiRestApiClientsProperties.class })
public class DeiRestApiClientsConfiguration {

    @Autowired
    @Qualifier("deiRestApiClientRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private DeiRestApiClientsProperties settings;

    @Bean
    public DucsRestApiClient ducsRestApiClient() {
        return new DefaultDucsRestApiClient(restTemplate, settings.getDucsServerHost());
    }
}
