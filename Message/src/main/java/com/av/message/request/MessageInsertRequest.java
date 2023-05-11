package com.av.message.request;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;

import java.io.File;

@Scope(scopeName = WebRequest.REFERENCE_REQUEST)
public record MessageInsertRequest(
        @Nullable String text ,
        @Nullable File file
) {
}
