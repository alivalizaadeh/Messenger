package com.av.user.request;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public record UserCreateRequest(
        @NotNull String firstName,
        @Nullable String lastName,
        @Nullable String bio,
        @Nullable String username,
        @NotNull String phoneNumber
) {
}
