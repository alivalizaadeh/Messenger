package com.av.message.aop;

import com.av.message.MessageApplication;
import com.av.message.entity.Message;
import com.av.message.entity.MessageLog;
import com.av.message.model.CustomDate;
import com.av.message.model.CustomTime;
import com.av.message.repository.MessageLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class MessageAop {
    private final MessageLogRepository messageLogRepository;

    @Autowired
    public MessageAop(MessageLogRepository messageLogRepository) {
        this.messageLogRepository = messageLogRepository;
    }

    @Pointcut("execution(* com.av.message.service.*.*(..))")
    public void logging(){}

    @AfterReturning(value = "logging()" , returning = "string")
    public void afterReturning(JoinPoint joinPoint , String string){
        MessageLog messageLog = createAbstractMessageLog(joinPoint);
        messageLog.setStatus("SUCCESSFUL");
        messageLog.setOutput(string);
        while (messageLogRepository.findById(messageLog.getId()).isPresent()){
            messageLog.setId(MessageApplication.getRandomStringId());
        }
        messageLogRepository.save(messageLog);
        log.info(messageLog.toString());
    }

    @AfterThrowing(value = "logging()" , throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint , Exception exception){
        MessageLog messageLog = createAbstractMessageLog(joinPoint);
        messageLog.setStatus("UNSUCCESSFUL");
        messageLog.setException(exception.getMessage());
        while (messageLogRepository.findById(messageLog.getId()).isPresent()){
            messageLog.setId(MessageApplication.getRandomStringId());
        }
        messageLogRepository.save(messageLog);
        log.error(messageLog.toString());
    }

    @AfterReturning(value = "logging()" , returning = "message")
    public void afterReturningFindById(JoinPoint joinPoint, Message message){
        MessageLog messageLog = createAbstractMessageLog(joinPoint);
        messageLog.setStatus("SUCCESSFUL");
        messageLog.setOutput(message.toString());
        while (messageLogRepository.findById(messageLog.getId()).isPresent()){
            messageLog.setId(MessageApplication.getRandomStringId());
        }
        messageLogRepository.save(messageLog);
        log.info(messageLog.toString());
    }

    @AfterReturning(value = "logging()" , returning = "messages")
    public void afterReturningFindAll(JoinPoint joinPoint, List<Message> messages){
        MessageLog messageLog = createAbstractMessageLog(joinPoint);
        messageLog.setStatus("SUCCESSFUL");
        messageLog.setOutput(messages.toString());
        while (messageLogRepository.findById(messageLog.getId()).isPresent()){
            messageLog.setId(MessageApplication.getRandomStringId());
        }
        messageLogRepository.save(messageLog);
        log.info(messageLog.toString());
    }

    private MessageLog createAbstractMessageLog(JoinPoint joinPoint){
        return MessageLog.builder()
                .id(MessageApplication.getRandomStringId())
                .className(joinPoint.getSignature().getDeclaringTypeName())
                .method(joinPoint.getSignature().getName())
                .input(Arrays.toString(joinPoint.getArgs()))
                .date(new CustomDate(LocalDate.now()).toString())
                .time(new CustomTime(LocalTime.now()).toString())
                .build();
    }
}
