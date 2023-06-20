package com.av.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;
import java.util.UUID;

@EnableEurekaClient
@SpringBootApplication
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class , args);
    }

    public static String getRandomStringId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static LocalDateTime customizeLocalDateTime(LocalDateTime localDateTime){
        return LocalDateTime.of(
                localDateTime.toLocalDate().getYear() ,
                localDateTime.getMonth() ,
                localDateTime.getDayOfMonth() ,
                localDateTime.toLocalTime().getHour() ,
                localDateTime.toLocalTime().getMinute() ,
                localDateTime.toLocalTime().getSecond()
        );
    }
}
