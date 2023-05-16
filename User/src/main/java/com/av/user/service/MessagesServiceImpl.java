package com.av.user.service;

import com.av.user.entity.MessageTypes;
import com.av.user.exception.Message.MessageNotFoundException;
import com.av.user.exception.User.UserNotFoundException;
import com.av.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final UserService userService;

    @Autowired
    public MessagesServiceImpl(UserRepository userRepository, RestTemplate restTemplate, UserService userService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @Override
    public void addMessage(Long userId, String messageId)
            throws MessageNotFoundException, UserNotFoundException {
        // fixme : check it was working or not
        checkMessageIdIsExist(messageId);
        checkUserIdIsExist(userId);
        // fixme : add message to db
        // info : it's saving as messageId + userId because we need customize messages for users
        userRepository.saveMessage(userId, messageId + userId);
    }

    @Override
    public void addTypeMessage(Long userId, String messageId, List<MessageTypes> messageTypes)
            throws MessageNotFoundException , UserNotFoundException{
        // todo : check userId is exist
        checkUserIdIsExist(userId);

        // todo : check messageId is exits
        checkMessageIdIsExist(messageId + userId);

        // todo : insert message types to message
        for (MessageTypes messageType : messageTypes) {
            userRepository.saveMessageType(messageId + userId, messageType.toString());
        }
    }

    private void checkUserIdIsExist(Long userId) throws UserNotFoundException{
        userService.findById(userId);
    }

    private void checkMessageIdIsExist(String messageId) throws MessageNotFoundException{
        restTemplate.getForObject(
                "http://localhost:8081/messages/{id}/id",
                String.class ,
                messageId
        );
    }
}
