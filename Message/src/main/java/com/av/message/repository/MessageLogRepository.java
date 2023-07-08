package com.av.message.repository;

import com.av.message.entity.MessageLog;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public interface MessageLogRepository extends MongoRepository<MessageLog , String> {
}
