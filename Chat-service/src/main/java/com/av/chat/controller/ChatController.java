package com.av.chat.controller;

import com.av.chat.request.ChatRequestInput;
import com.av.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> sendMessage(@RequestBody ChatRequestInput request) throws IOException {
        chatService.sendMessage(request);
        return ResponseEntity.ok("Message sent.");
    }
}
