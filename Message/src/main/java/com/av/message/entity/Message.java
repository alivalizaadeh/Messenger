package com.av.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@ToString
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class Message {
    @Id
    @Column(unique = true, updatable = false , length = 200)
    private String id;

    @Column
    private String text;

    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime sentAt;
    
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime readAt;

    @Column(nullable = false)
    private Boolean hasRead;

    @Column(nullable = false)
    private Boolean isEdited;

    @Column(nullable = false)
    private Boolean isDeleted;
}
