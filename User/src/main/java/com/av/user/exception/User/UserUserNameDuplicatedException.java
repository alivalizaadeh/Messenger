package com.av.user.exception.User;

public class UserUserNameDuplicatedException extends UserDuplicatedException{
    public UserUserNameDuplicatedException() {
        super();
    }

    public UserUserNameDuplicatedException(String message) {
        super(message);
    }

    public UserUserNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
