package com.av.chat.service;

import com.av.chat.config.RabbitMQConfig;
import com.av.chat.request.ChatRequestInput;
import com.av.chat.request.ChatRequestUpdate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService{

    private final RabbitTemplate template;
    private final RestTemplate restTemplate;

    @Autowired
    public ChatServiceImpl(RabbitTemplate template, RestTemplate restTemplate) {
        this.template = template;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(ChatRequestInput request) {
        restTemplate.getForObject(
                "http://localhost:8082/users/{userId}/exists" ,
                Boolean.class ,
                request.userIdFrom()
        );
        restTemplate.getForObject(
                "http://localhost:8082/users/{userId}/exists" ,
                Boolean.class ,
                request.userIdTo()
        );
        template.convertAndSend(
                RabbitMQConfig.EXCHANGE ,
                RabbitMQConfig.ROUTING_KEY ,
                request
        );
    }
}
