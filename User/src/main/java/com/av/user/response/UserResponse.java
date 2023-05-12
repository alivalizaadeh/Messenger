package com.av.user.response;

public record UserResponse(
        Long id ,
        String firstname ,
        String lastname ,
        String bio ,
        String phoneNumber ,
        byte [] profilePicture
) {
}
