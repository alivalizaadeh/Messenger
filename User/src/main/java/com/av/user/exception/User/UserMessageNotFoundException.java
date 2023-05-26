package com.av.user.exception.User;

public class UserMessageNotFoundException extends RuntimeException{
    public UserMessageNotFoundException() {
        super();
    }

    public UserMessageNotFoundException(String message) {
        super(message);
    }

    public UserMessageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
