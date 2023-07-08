package com.av.message.entity;

import com.av.message.model.CustomDate;
import com.av.message.model.CustomTime;
import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document(collection = "messageLog")
@Builder
@ToString
@Data
@Setter
@Getter
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
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
