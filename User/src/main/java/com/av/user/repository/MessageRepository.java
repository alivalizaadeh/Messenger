package com.av.user.repository;

import com.av.user.entity.MessageType;
import com.av.user.response.MessageResponse;

import java.util.List;

public interface MessageRepository {
    MessageResponse insertMessage(Long userId , String messageId , List<MessageType> messageTypes);
    Boolean checkMessageHaveThisType(Long userId , String messageId , MessageType messageTypes);
}
