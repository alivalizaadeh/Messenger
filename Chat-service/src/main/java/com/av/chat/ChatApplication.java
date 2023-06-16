package com.av.chat;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ChatApplication {
    public static void main(String[] args) {
        String userToJsonRequest = "{\"userId\":" + 1 + ", \"messageId\":" + "812738123"
                + ",\"messageTypes\":[\"RECEIVED_MESSAGE\"]}";
        System.out.println(userToJsonRequest);
        SpringApplication.run(ChatApplication.class , args);
    }
}
