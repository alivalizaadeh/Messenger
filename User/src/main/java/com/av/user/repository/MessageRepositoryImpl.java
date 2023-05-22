package com.av.user.repository;

import com.av.user.entity.MessageType;
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
    public MessageResponse forCheck(Long userId, String messageId) {
        List<MessageResponse> responses = jdbcTemplate.query(
                "select * from user.messages" ,
                new MessagesRowMapper()
        );
        System.out.println(responses);
        return new MessageResponse(userId , messageId + userId , new ArrayList<>());
    }

    @Override
    public MessageResponse insertMessage(Long userId , String messageId , List<MessageType> messageTypes){
        // todo : check message exists or not



        // todo : after check insert to messages

        // todo : after insert, check to messageTypes exists or not, if exists nothing otherwise insert type messages

        jdbcTemplate.queryForObject(
                "select * from user.messages where user_id = ? and message_id = ?" ,
                MessageResponse.class ,
                userId , messageId + userId
        );
        jdbcTemplate.update(
                "insert into user.messages (user_id , message_id) values (?,?)" ,
                userId , messageId);
        for (MessageType messageType : messageTypes){
            jdbcTemplate.update(
                    "insert into user.type_messages (message_id , message_type) values (?,?)" ,
                    messageId + userId , messageType
            );
        }
        return new MessageResponse(userId, messageId + userId , messageTypes);
    }

    @Override
    public Boolean checkUserHaveTheMessage(Long userId, String messageId) {
        MessageResponse response = jdbcTemplate.queryForObject(
                "select * from user.messages where user_id = ? and message_id = ?" ,
                new MessagesRowMapper() ,
                userId , messageId + userId
        );
        return response != null;
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
