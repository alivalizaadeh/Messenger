package com.av.chat.request;

import org.springframework.lang.Nullable;

import java.io.File;
import java.io.Serializable;

public record ChatRequestInput(
        Long userIdFrom ,
        Long userIdTo ,
        @Nullable String text ,
        @Nullable File file
) implements Serializable{}
