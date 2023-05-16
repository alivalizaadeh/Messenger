package com.av.user.controller;

import com.av.user.request.MessageRequest;
import com.av.user.service.MessagesService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/messages")
@DynamicUpdate
public class MessagesController {
    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Boolean> saveMessageForUser(@RequestBody MessageRequest request){
        messagesService.addMessage(request.userId(), request.messageId() , request.messageTypes());
        return ResponseEntity.ok(true);
    }
}
