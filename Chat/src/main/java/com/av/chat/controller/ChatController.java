package com.av.chat.controller;

import com.av.chat.publisher.RabbitMQProducer;
import com.av.chat.request.RequestToRabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final RabbitMQProducer producer;

    @Autowired
    public ChatController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody RequestToRabbitMQ request){
        producer.sendMessage(request.userIdFrom(), request.userIdTo(), request.messageId());
        return ResponseEntity.ok("Message sent.");
    }
}
