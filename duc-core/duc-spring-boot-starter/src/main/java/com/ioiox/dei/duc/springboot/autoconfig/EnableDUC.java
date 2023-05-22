package com.ioiox.dei.duc.springboot.autoconfig;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.*;

@EnableWebSecurity

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ DUCMarkerConfiguration.class })
public @interface EnableDUC {

}
