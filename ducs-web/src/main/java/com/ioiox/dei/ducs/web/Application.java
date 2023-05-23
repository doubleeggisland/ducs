package com.ioiox.dei.ducs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages = { "com.ioiox.dei.ducs.web", "com.ioiox.dei.duc.db", "com.ioiox.dei.duc.std.data.svc" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
