package com.av.user.repository;

import com.av.user.entity.MessageType;
import com.av.user.exception.User.UserMessageNotFoundException;
import com.av.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MessageResponse insertMessage(Long userId , String messageId , List<MessageType> messageTypes) {
        // todo : check message for user exists or not
        if(!isUserHaveTheMessage(userId, messageId)){
            jdbcTemplate.update(
                    "insert into user.messages (user_id , message_id) values (?,?)" ,
                    userId , messageId + userId);
        }

        // todo : check to messageTypes exists or not, if exists nothing otherwise insert type messages
        List<String> responses = jdbcTemplate
                .queryForList(
                        "select message_type from user.type_messages where message_id = ?" ,
                        String.class ,
                        messageId + userId
                );

        // todo : filter messageType when it equals with types response
        for (int i = 0 ; i < messageTypes.size() ; i++)
            for (String s : responses)
                if (messageTypes.get(i).name().equalsIgnoreCase(s))
                    messageTypes.remove(i);


        // todo : insert messageTypes into type_messages when filtered
        for (MessageType messageType : messageTypes)
            jdbcTemplate.update(
                    "insert into user.type_messages values (?,?)" ,
                    messageId + userId , messageType.name()
            );

        List<MessageType> allTypes = new ArrayList<>();
        for (String string : responses)
            allTypes.add(MessageType.valueOf(string));
        allTypes.addAll(messageTypes);

        return new MessageResponse(userId, messageId , allTypes);
    }

    @Override
    public Boolean isUserHaveTheMessage(Long userId, String messageId) {
        List<Long> responses = jdbcTemplate.queryForList(
                "select user_id from user.messages where message_id = ?" ,
                Long.class ,
                messageId + userId
        );
        return responses.size() == 1;
    }

    @Override
    public MessageResponse deleteMessage(Long userId, String messageId , MessageType messageType) {
        if (!isUserHaveTheMessage(userId, messageId)){
            throw new UserMessageNotFoundException(
                    "The Message with id '" + messageId + "' for user '" + userId + "' not found."
            );
        }
        jdbcTemplate.update(
                "delete from user.messages where user_id = ? and message_id = ?" ,
                userId ,
                messageId + userId
        );
        int value = jdbcTemplate.update(
                "delete from user.type_messages where message_id = ? and message_type = ?" ,
                messageId + userId ,
                messageType.name()
        );
        System.out.println("Value : " + value);
        return new MessageResponse(
                userId ,
                messageId ,
                List.of(messageType)
        );
    }
}
