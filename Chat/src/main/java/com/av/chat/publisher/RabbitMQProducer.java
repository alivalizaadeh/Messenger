package com.av.chat.publisher;

public interface RabbitMQProducer {
    void sendMessage(Long userIdFrom , Long userIdTo , String messageId);
}
