package com.av.user.exception;

public class UserPhoneNumberDuplicatedException extends UserDuplicatedException{
    public UserPhoneNumberDuplicatedException() {
        super();
    }

    public UserPhoneNumberDuplicatedException(String message) {
        super(message);
    }

    public UserPhoneNumberDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
