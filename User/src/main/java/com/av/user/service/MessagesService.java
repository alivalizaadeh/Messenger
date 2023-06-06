package com.av.user.service;


import com.av.user.entity.MessageType;
import com.av.user.response.MessageResponse;

import java.util.List;

public interface MessagesService {
    MessageResponse addMessage(Long userId , String messageId , List<MessageType> messageTypes);
    MessageResponse deleteMessage(Long userId , String messageId , List<MessageType> messageTypes);
    List<MessageResponse> getMessagesForUser(Long userId);
}
