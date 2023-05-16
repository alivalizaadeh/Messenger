package com.av.user.service;


import com.av.user.entity.MessageType;

import java.util.List;

public interface MessagesService {
    void addMessage(Long userId , String messageId , List<MessageType> messageTypes);
}
