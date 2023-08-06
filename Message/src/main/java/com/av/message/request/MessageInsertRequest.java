package com.av.message.request;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;

@Scope(scopeName = WebRequest.REFERENCE_REQUEST)
public record MessageInsertRequest(
        @NotNull String text ,
        @Nullable String id
) {
}
