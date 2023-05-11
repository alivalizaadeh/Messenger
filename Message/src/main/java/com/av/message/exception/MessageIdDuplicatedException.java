package com.av.message.exception;

public class MessageIdDuplicatedException extends RuntimeException{
    public MessageIdDuplicatedException() {
    }

    public MessageIdDuplicatedException(String message) {
        super(message);
    }

    public MessageIdDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
