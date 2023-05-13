package com.av.user.request;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;

@Scope(WebRequest.REFERENCE_REQUEST)
public record UserUpdateRequest(
        @NotNull Long id ,
        @Nullable String firstName ,
        @Nullable String lastname ,
        @Nullable String bio ,
        @Nullable String username ,
        @Nullable String phoneNumber ,
        @Nullable byte [] profilePicture
) {
}
