package com.av.message.advice;

import com.av.message.exception.MessageIdDuplicatedException;
import com.av.message.exception.MessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MessageExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> handleMethodArgumentNotValidException(/* Use for Validation Exception */
            MethodArgumentNotValidException exception){
        Map<String , String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField() , fieldError.getDefaultMessage());
        });
        return errors;
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public Map<String , String> handleMessageNotFoundException(MessageNotFoundException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("errorMessage" , exception.getMessage());
        return errors;
    }

    @ExceptionHandler(MessageIdDuplicatedException.class)
    public Map<String , String> handleMessageIdDuplicatedException(MessageIdDuplicatedException exception){
        Map<String , String> errors = new HashMap<>();
        errors.put("errorMessage" , exception.getMessage());
        return errors;
    }
}
