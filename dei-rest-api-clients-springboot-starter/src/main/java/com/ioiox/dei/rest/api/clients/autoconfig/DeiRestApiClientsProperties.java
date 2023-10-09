package com.ioiox.dei.rest.api.clients.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.ioiox.dei.rest.api.client")
public class DeiRestApiClientsProperties {
    private String ducsServerHost;
}
