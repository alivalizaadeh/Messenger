package com.av.message.aop;

import com.av.message.entity.Message;
import com.av.message.entity.MessageLog;
import com.av.message.repository.MessageLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class MessageAop {
    private static Integer counter = 1;

    private final MessageLogRepository messageLogRepository;

    @Autowired
    public MessageAop(MessageLogRepository messageLogRepository) {
        this.messageLogRepository = messageLogRepository;
    }

    @Pointcut("execution(* com.av.message.service.*.*(..))")
    public void logging(){}

    /*
    @Before("logging()")
    public void before(JoinPoint joinPoint){
        messageLogRepository.insert(MessageLog.builder()
                .method(joinPoint.getSignature().getName())
                .message(joinPoint.getArgs()[0] + " are input for method.").build());
    }
     */

    @AfterReturning(value = "logging()" , returning = "string")
    public void afterReturningInsert(JoinPoint joinPoint , String string){
        messageLogRepository.save(MessageLog.builder()
                .id(counter++)
                .method(joinPoint.getSignature().getName())
                .message("Return value : " + string).build());
    }

    @AfterThrowing(value = "logging()" , throwing = "exception")
    public void afterReturningThrow(JoinPoint joinPoint , Exception exception){
        messageLogRepository.save(MessageLog.builder()
                .id(counter++)
                .method(joinPoint.getSignature().getName())
                .message("Return exception : " + exception.getMessage()).build());
    }

    @AfterReturning(value = "logging()" , returning = "message")
    public void afterReturningFind(JoinPoint joinPoint, Message message){
        messageLogRepository.save(MessageLog.builder()
                .id(counter++)
                .method(joinPoint.getSignature().getName())
                .message("Return object : " + message).build());
    }

}
