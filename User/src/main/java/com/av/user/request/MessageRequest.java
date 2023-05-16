package com.av.user.request;

import com.av.user.entity.MessageTypes;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

public record MessageRequest(
        @NotNull Long userId ,
        @NotNull String messageId //,
        //@Nullable List<MessageTypes> messageTypes
        ) {
}
