package com.av.message.entity;

import com.av.message.model.CustomDate;
import com.av.message.model.CustomTime;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Document(collection = "messageLog")
@Builder
@ToString
@Data
@Setter
@Getter
public class MessageLog {
    @Id
    private String id;
    private String status;
    private String className;
    private String method;
    private Object input;
    private Object output;
    private String exception;
    private String date;
    private String time;
}
