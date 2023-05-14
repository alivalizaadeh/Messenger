package com.av.message.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class MessageConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /*
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/message");
        source.setUsername("root");
        source.setPassword("Ali_vlizadeh@25975800");
        return source;
    }
     */
}
