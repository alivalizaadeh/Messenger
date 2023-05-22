package com.av.user.exception.User;

public class UserMessageFoundException extends RuntimeException{
    public UserMessageFoundException() {
        super();
    }

    public UserMessageFoundException(String message) {
        super(message);
    }

    public UserMessageFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
