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
    public String insert(MessageInsertRequest request) throws MessageIdDuplicatedException {
        String id = MessageApplication.getRandomStringId();
        try {
            findById(id);
            throw new MessageIdDuplicatedException();
        } catch (MessageNotFoundException ignored){
            Message message = Message.builder().
                    id(id).
                    text(request.text()).
                    sentAt(MessageApplication.customizeLocalDateTime(LocalDateTime.now())).
                    hasRead(false).isEdited(false).isDeleted(false)
                    .build();
            messageRepository.save(message);
            return id;
        }
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
    public String update(MessageUpdateRequest request) throws MessageNotFoundException {
        if (Boolean.TRUE.equals(request.isDeleted())){
            delete(request.id());
            return null;
        }
        Message find = findById(request.id());
        Message message = Message.builder().
                id(request.id()).
                text(request.text()).
                sentAt(find.getSentAt()).
                readAt(request.readAt() == null ? find.getReadAt() :
                        MessageApplication.customizeLocalDateTime(request.readAt())).
                hasRead((request.readAt() == null) ? find.getHasRead() : true).
                isEdited(true).
                isDeleted(request.isDeleted() == null ? find.getIsDeleted() : request.isDeleted()).build();
        messageRepository.save(message);
        return request.id();
    }

    @Override
    public void delete(String id) {
        messageRepository.deleteById(id);
    }
}
