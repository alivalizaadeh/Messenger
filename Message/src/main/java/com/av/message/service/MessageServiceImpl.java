package com.av.message.service;

import com.av.message.MessageApplication;
import com.av.message.entity.Message;
import com.av.message.exception.MessageIdDuplicatedException;
import com.av.message.exception.MessageNotFoundException;
import com.av.message.repository.MessageRepository;
import com.av.message.request.MessageInsertRequest;
import com.av.message.request.MessageUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    /*
    @Transactional(isolation = Isolation.READ_COMMITTED ,
            propagation = Propagation.REQUIRES_NEW
    )
     */
    public String insert(MessageInsertRequest request) {
        Message message = Message.builder().
                id(MessageApplication.getRandomStringId()).
                text(request.text()).
                file(request.file()).
                sentAt(MessageApplication.customizeLocalDateTime(LocalDateTime.now())).
                readAt(null).
                hasRead(false).isEdited(false).isDeleted(false)
                .build();
        try {
            findById(message.getId());
            throw new MessageIdDuplicatedException();
        }catch (MessageNotFoundException e){
            messageRepository.save(message);
        }
        return message.getId();
    }

    @Override
    public Message findById(String id) throws MessageNotFoundException {
        return messageRepository.findById(id).orElseThrow(() ->
                new MessageNotFoundException("Message not found with id : " + id));
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public String update(MessageUpdateRequest request) {
        if (Boolean.TRUE.equals(request.isDeleted())){
            delete(request.id());
        }
        try {
            Message find = findById(request.id());
            Message message = Message.builder().
                    id(request.id()).
                    text(request.text()).
                    sentAt(find.getSentAt()).
                    readAt(request.readAt() == null ? find.getReadAt() :
                            MessageApplication.customizeLocalDateTime(request.readAt())).
                    file(find.getFile()).
                    hasRead((request.readAt() == null) ? find.getHasRead() : true).
                    isEdited(true).
                    isDeleted(request.isDeleted() == null ? find.getIsDeleted() : request.isDeleted()).build();
            messageRepository.save(message);
        } catch (MessageNotFoundException exception){
            throw new MessageNotFoundException("Message not found with id : " + request.id());
        }
        return request.id();
    }

    @Override
    public void delete(String id) {
        messageRepository.deleteById(id);
    }
}
