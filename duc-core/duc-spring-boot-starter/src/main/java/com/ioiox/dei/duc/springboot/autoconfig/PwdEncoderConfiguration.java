package com.ioiox.dei.duc.springboot.autoconfig;

import com.ioiox.dei.duc.springboot.support.DefaultPwdEncoderImpl;
import com.ioiox.dei.duc.springboot.support.PwdEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PwdEncoderConfiguration {

    @Bean
    public PwdEncoder pwdEncoder() {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new DefaultPwdEncoderImpl(bCryptPasswordEncoder);
    }
}
