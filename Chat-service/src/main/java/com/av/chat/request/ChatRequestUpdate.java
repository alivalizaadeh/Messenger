package com.av.chat.request;

import org.springframework.lang.Nullable;

import java.io.File;
import java.io.Serializable;

public record ChatRequestUpdate(
        Long useridFrom ,
        Long userIdTo ,
        String messageId
) implements Serializable {
}
