package com.av.user.exception.User;

public class UserDuplicatedException extends RuntimeException{
    public UserDuplicatedException() {
        super();
    }

    public UserDuplicatedException(String message) {
        super(message);
    }

    public UserDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
