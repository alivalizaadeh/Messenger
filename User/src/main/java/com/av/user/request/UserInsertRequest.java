package com.av.user.request;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;

@Scope(WebRequest.REFERENCE_REQUEST)
public record UserInsertRequest(
        @NotNull String firstName,
        @Nullable String lastName,
        @NotNull String phoneNumber
) {
}
