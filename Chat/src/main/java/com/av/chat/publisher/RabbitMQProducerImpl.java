package com.av.chat.publisher;

import com.av.chat.request.RequestToRabbitMQ;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RabbitMQProducerImpl implements RabbitMQProducer{
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public RabbitMQProducerImpl(RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(Long userIdFrom, Long userIdTo, String messageId) {
        restTemplate.getForObject(
                "http://localhost:8081/messages?id={messageId}" ,
                String.class ,
                messageId
        );
        rabbitTemplate.convertAndSend(exchange , routingKey , new RequestToRabbitMQ(
                userIdFrom,
                userIdTo,
                messageId)
        );
    }
}
