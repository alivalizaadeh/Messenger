package com.av.message.service;

import com.av.message.MessageApplication;
import com.av.message.entity.Message;
import com.av.message.exception.MessageIdDuplicatedException;
import com.av.message.exception.MessageNotFoundException;
import com.av.message.repository.MessageRepository;
import com.av.message.request.MessageInsertRequest;
import com.av.message.request.MessageUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;
    private MessageService messageService;

    private AutoCloseable autoCloseable;
    private Message message;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        messageService = new MessageServiceImpl(messageRepository);
        message = Message.builder().
                id(MessageApplication.getRandomStringId()).
                text("Hello").
                sentAt(LocalDateTime.now()).
                readAt(null).
                isEdited(false).
                isDeleted(false).
                hasRead(false).
                build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testInsert_NotDuplicated() {
        when(messageRepository.save(message)).thenReturn(message);

        assertThat(messageService.insert(
                new MessageInsertRequest(message.getText() , message.getId())
        )).isEqualTo(message.getId());
    }

    @Test
    void testInsert_Duplicated() {
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));

        assertThrows(MessageIdDuplicatedException.class ,
                () -> messageService.insert(
                        new MessageInsertRequest(message.getText() , message.getId())
                ));
    }

    @Test
    void testFindById_Found() {
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));
        assertThat(messageService.findById(message.getId()).getId()).isEqualTo(message.getId());
    }

    @Test
    void testFindById_NotFound() {
        assertThrows(MessageNotFoundException.class , () -> messageService.findById(message.getId()));
    }

    @Test
    void testFindAll_Found() {
        when(messageRepository.findAll()).thenReturn(List.of(message));
        assertThat(messageService.findAll().size()).isEqualTo(1);
    }

    @Test
    void testFindAll_NotFound() {
        when(messageRepository.findAll()).thenReturn(List.of());
        assertThat(messageService.findAll().size()).isEqualTo(0);
    }

    @Test
    void testUpdate_DeletedMessage() {
        assertThat(messageService.update(
                MessageUpdateRequest.builder().isDeleted(true).build()
        )).isNull();
    }

    @Test
    void testUpdate_EditedMessage() {
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));
        assertThat(messageService.update(
                MessageUpdateRequest.builder()
                        .id(message.getId())
                        .text("XD")
                        .build()
        )).isNotEqualTo(message);
    }
}