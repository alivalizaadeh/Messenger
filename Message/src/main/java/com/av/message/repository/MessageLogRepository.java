package com.av.message.repository;

import com.av.message.entity.MessageLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageLogRepository extends MongoRepository<MessageLog , Integer> {
}
