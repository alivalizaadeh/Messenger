package com.av.user.repository;

import com.av.user.response.MessageResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessagesRowMapper implements RowMapper<MessageResponse> {
    @Override
    public MessageResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        MessageResponse messageResponse = new MessageResponse(
                rs.getLong("user_id") ,
                rs.getString("message_id") ,
                new ArrayList<>()
        );
        return messageResponse;
    }
}
