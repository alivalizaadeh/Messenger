package com.av.message.request;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Scope(WebRequest.REFERENCE_REQUEST)
public record MessageUpdateRequest(
        @NotNull String id ,
        @Nullable String text ,
        @Nullable LocalDateTime readAt ,
        @Nullable Boolean hasRead ,
        @Nullable Boolean isEdited ,
        @Nullable Boolean isDeleted
) {
}
