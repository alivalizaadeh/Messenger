package com.av.message.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "messageLog")
@Builder
@ToString
@Data
public class MessageLog {
    @Id
    private Integer id;
    private String method;
    private String message;
}
