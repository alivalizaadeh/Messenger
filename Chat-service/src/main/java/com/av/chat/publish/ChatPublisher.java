package com.av.chat.publish;

import com.av.chat.request.ChatRequestInput;

import java.io.IOException;

public interface ChatPublisher {
    void publish(ChatRequestInput request) throws IOException;
}
