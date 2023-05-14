package com.av.user.request;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public record UserInsertRequest(
        @NotNull String firstName,
        @Nullable String lastName,
        @NotNull String phoneNumber
) {
}
