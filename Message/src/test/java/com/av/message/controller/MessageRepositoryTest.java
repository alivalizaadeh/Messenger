package com.av.message.controller;

import com.av.message.MessageApplication;
import com.av.message.entity.Message;
import com.av.message.repository.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ComponentScan
public class MessageRepositoryTest {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageRepositoryTest(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @AfterEach
    void tearDown(){
        messageRepository.deleteAll();
    }
    @Test
    void testCreateWithCorrectInformation() {
        Message message = Message.builder().
                id("bd6b356914f1450ca772a13739ca37bd").
                text("Hi , I'm Ali.").
                sentAt(MessageApplication.customizeLocalDateTime(LocalDateTime.now())).
                hasRead(false).isEdited(false).isDeleted(false).build();
        messageRepository.save(message);
        assertNotNull(messageRepository.findById("bd6b356914f1450ca772a13739ca37bd").orElse(null));
    }

    @Test
    void testGetOneMessageWithCorrectId() {
        Message message = Message.builder().
                id("bd6b356914f1450ca772a13739ca37bd").
                text("Hi , I'm Ali.").
                file(null).
                sentAt(MessageApplication.customizeLocalDateTime(LocalDateTime.now())).
                readAt(null).hasRead(false).isEdited(false).isDeleted(false).build();
        messageRepository.save(message);
        assertThat(message).isEqualTo(messageRepository.findById(message.getId()).orElse(null));
    }

    @Test
    void testGetOneMessageWithWrongId(){
        assertThat(messageRepository.findById("asd").orElse(null)).isNull();
    }

    @Test
    void testUpdateMessage(){
        Message message = Message.builder().
                id("bd6b356914f1450ca772a13739ca37bd").
                text("Hi , I'm Ali.").
                file(null).
                sentAt(MessageApplication.customizeLocalDateTime(LocalDateTime.now())).
                readAt(null).hasRead(false).isEdited(false).isDeleted(false).build();
        messageRepository.save(message);
        Message update = Message.builder().
                id(message.getId()).
                text("Hi , I'm Ali and play guitar somtimes.").
                file(message.getFile()).
                sentAt(message.getSentAt()).
                readAt(null).hasRead(false).isEdited(true).isDeleted(false).build();
        messageRepository.save(update);
        assertThat(message).isNotEqualTo(messageRepository.findById(message.getId()).orElse(null));
    }
}
