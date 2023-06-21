package com.av.user.controller;

import com.av.user.request.MessageRequest;
import com.av.user.response.MessageResponse;
import com.av.user.service.MessagesService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/messages")
@DynamicUpdate
public class MessagesController {
    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/insert")
    public ResponseEntity<MessageResponse> saveMessageForUser(@RequestBody MessageRequest request){
        return new ResponseEntity<>(
                messagesService.addMessage(request.userId(), request.messageId() , request.messageTypes()) ,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/allMessages/{userId}")
    public ResponseEntity<List<MessageResponse>> getMessagesForUser(@PathVariable Long userId){
        return new ResponseEntity<>(messagesService.getMessagesForUser(userId) , HttpStatus.FOUND);
    }

}
