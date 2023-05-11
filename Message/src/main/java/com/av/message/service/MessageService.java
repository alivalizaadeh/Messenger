package com.av.message.service;

import com.av.message.entity.Message;
import com.av.message.request.MessageInsertRequest;
import com.av.message.request.MessageUpdateRequest;

import java.util.List;

public interface MessageService {
    String insert(MessageInsertRequest request);
    Message findById(String id);
    List<Message> findAll();
    String update(MessageUpdateRequest request);
    void delete(String id);
}
