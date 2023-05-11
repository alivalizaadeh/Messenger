package com.av.message.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MessageConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
