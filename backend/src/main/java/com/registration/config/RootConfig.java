package com.registration.config;

import com.registration.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RootConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5Encoder();
    }

}


