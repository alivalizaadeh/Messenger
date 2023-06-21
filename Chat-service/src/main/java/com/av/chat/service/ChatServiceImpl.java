package com.av.chat.service;

import com.av.chat.config.RabbitMQConfig;
import com.av.chat.request.ChatRequestInput;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
                "http://localhost:8080/users/{userId}/exists" ,
                Boolean.class ,
                request.userIdFrom()
        );
        restTemplate.getForObject(
                "http://localhost:8080/users/{userId}/exists" ,
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
