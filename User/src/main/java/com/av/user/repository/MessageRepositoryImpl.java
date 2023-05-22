package com.av.user.repository;

import com.av.user.entity.MessageType;
import com.av.user.exception.User.UserMessageFoundException;
import com.av.user.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MessageResponse insertMessage(Long userId , String messageId , List<MessageType> messageTypes)
            throws UserMessageFoundException{
        // todo : check message for user exists or not
        if(isUserHaveTheMessage(userId, messageId)){
            jdbcTemplate.update(
                    "insert into user.messages (user_id , message_id) values (?,?)" ,
                    userId , messageId + userId);
        }

        // todo : after insert, check to messageTypes exists or not, if exists nothing otherwise insert type messages
        List<String> responses = jdbcTemplate
                .queryForList(
                        "select message_type from user.type_messages where message_id = ?" ,
                        String.class ,
                        messageId + userId
                );

        // todo : filter messageType when it equals with types response
        messageTypes.stream().filter(messageType -> messageType.name().equals(responses.stream()));

        // todo : insert messageTypes into type_messages when filtered
        for (MessageType messageType : messageTypes)
            jdbcTemplate.update(
                    "insert into user.type_messages values (?,?)" ,
                    messageId + userId , messageType
            );

        return new MessageResponse(userId, messageId , messageTypes);
    }

    @Override
    public Boolean isUserHaveTheMessage(Long userId, String messageId) {
        MessageResponse response = jdbcTemplate.queryForObject(
                "select * from user.messages where user_id = ? and message_id = ?" ,
                new MessagesRowMapper() ,
                userId , messageId + userId
        );
        return response == null;
    }

    @Override
    public Boolean checkMessageHaveThisType(Long userId, String messageId, MessageType messageTypes) {
        MessageResponse response = jdbcTemplate.queryForObject(
                "select message_id , message_type from user.type_messages where message_id : ?" ,
                MessageResponse.class ,
                messageId + userId
        );
        System.out.println(response);
        return true;
    }
}
