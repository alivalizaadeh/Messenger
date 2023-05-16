package com.av.user.response;

import com.av.user.entity.MessageType;
import org.springframework.lang.Nullable;

import java.util.List;

public record MessageResponse(
        Long userId ,
        String messageId ,
        @Nullable List<MessageType> messageTypes
        ) {
}
