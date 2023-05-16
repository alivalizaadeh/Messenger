package com.av.user.repository;

import com.av.user.entity.MessageTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMessage(Long userId , String messageId){
        jdbcTemplate.update(
                "insert into user.messages (user_id , message_id) values (?,?)" ,
                userId , messageId);
    }

    public void insertTypeMessage(Long userId , String messageId , List<MessageTypes> messageTypes){
        for (MessageTypes type : messageTypes){
            jdbcTemplate.update(
                    "insert into user.type_messages (message_id , message_type) values (?,?)" ,
                    messageId + userId , type
            );
        }
    }
}
