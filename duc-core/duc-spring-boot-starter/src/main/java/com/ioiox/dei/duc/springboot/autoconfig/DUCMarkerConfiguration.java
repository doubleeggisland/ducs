package com.ioiox.dei.duc.springboot.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DUCMarkerConfiguration {

    static class Marker {}

    @Bean
    public Marker enableUserCenter() {
        return new Marker();
    }
}
