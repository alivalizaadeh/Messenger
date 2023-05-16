package com.av.user.service;


import com.av.user.entity.MessageTypes;

import java.util.List;

public interface MessagesService {
    void addMessage(Long userId , String messageId);
    void addTypeMessage(Long userId , String messageId , List<MessageTypes> messageTypes);
}
