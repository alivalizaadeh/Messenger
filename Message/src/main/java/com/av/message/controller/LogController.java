package com.av.message.controller;

import com.av.message.entity.MessageLog;
import com.av.message.repository.MessageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final MessageLogRepository messageLogRepository;

    @Autowired
    public LogController(MessageLogRepository messageLogRepository) {
        this.messageLogRepository = messageLogRepository;
    }

    @GetMapping("/all")
    public List<MessageLog> findAll(){
        return messageLogRepository.findAll();
    }
}
