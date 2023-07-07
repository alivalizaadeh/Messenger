package com.av.message.repository;

import com.av.message.entity.MessageLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface MessageLogRepository extends MongoRepository<MessageLog , String> {
}
