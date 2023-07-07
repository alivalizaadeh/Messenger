package com.av.user.service;

import com.av.user.entity.MessageType;
import com.av.user.exception.Message.MessageNotFoundException;
import com.av.user.exception.User.UserMessageNotFoundException;
import com.av.user.exception.User.UserNotFoundException;
import com.av.user.repository.MessageRepositoryImpl;
import com.av.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    private final RestTemplate restTemplate;
    private final UserService userService;
    private final MessageRepositoryImpl messageRepository;

    @Autowired
    public MessagesServiceImpl(RestTemplate restTemplate, UserService userService, MessageRepositoryImpl messageRepository) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponse addMessage(Long userId, String messageId , List<MessageType> messageTypes)
            throws MessageNotFoundException, UserNotFoundException , UserMessageNotFoundException {
        isMessageIdExist(messageId);
        isUserIdExist(userId);
        return messageRepository.insertMessage(userId , messageId , messageTypes);
    }

    @Override
    public MessageResponse deleteMessage(Long userId, String messageId , List<MessageType> messageTypes) {
        isMessageIdExist(messageId);
        isUserIdExist(userId);
        return messageRepository.deleteMessage(userId, messageId , messageTypes);
    }

    private void isUserIdExist(Long userId) throws UserNotFoundException{
        userService.findById(userId);
    }

    private void isMessageIdExist(String messageId) throws MessageNotFoundException{
        restTemplate.getForObject(
                "http://localhost:8080/messages/{messageId}/exist",
                String.class ,
                messageId
        );
    }

    @Override
    public List<MessageResponse> getMessagesForUser(Long userId) {
        isUserIdExist(userId);
        return messageRepository.getMessagesForUser(userId);
    }
}
