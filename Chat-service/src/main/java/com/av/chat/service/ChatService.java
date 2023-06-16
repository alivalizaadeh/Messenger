package com.av.chat.service;

import com.av.chat.request.ChatRequestInput;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ChatService {
    void sendMessage(ChatRequestInput request) throws IOException;
}
