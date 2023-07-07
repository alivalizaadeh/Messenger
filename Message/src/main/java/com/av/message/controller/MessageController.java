package com.av.message.controller;

import com.av.message.entity.Message;
import com.av.message.exception.MessageIdDuplicatedException;
import com.av.message.exception.MessageNotFoundException;
import com.av.message.request.MessageInsertRequest;
import com.av.message.request.MessageUpdateRequest;
import com.av.message.service.MessageService;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@DynamicUpdate
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> saveMessage(@RequestBody MessageInsertRequest request)
            throws MessageIdDuplicatedException {
        return new ResponseEntity<>(messageService.insert(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable String id)
            throws MessageNotFoundException {
        return new ResponseEntity<>(messageService.findById(id) , HttpStatus.FOUND);
    }

    @GetMapping("/{id}/exist")
    public ResponseEntity<String> getMessageById(@PathVariable String id)
            throws MessageNotFoundException {
        return new ResponseEntity<>(messageService.findById(id).getId() , HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateMessage(@RequestBody MessageUpdateRequest request) {
        return new ResponseEntity<>(messageService.update(request), HttpStatus.UPGRADE_REQUIRED);
    }
}
