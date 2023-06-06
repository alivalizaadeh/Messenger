package com.av.chat.request;

public record RequestToRabbitMQ(
        Long userIdFrom ,
        Long userIdTo ,
        String messageId
) {
}
