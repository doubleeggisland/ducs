package com.ioiox.dei.ducs.web;

import com.ioiox.dei.duc.springboot.autoconfig.EnableDUC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EnableDUC
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages = { "com.ioiox.dei.ducs.web", "com.ioiox.dei.duc.db"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
