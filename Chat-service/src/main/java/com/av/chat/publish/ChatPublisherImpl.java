package com.av.chat.publish;

import com.av.chat.config.RabbitMQConfig;
import com.av.chat.request.ChatRequestInput;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ChatPublisherImpl implements ChatPublisher{

    private final RestTemplate restTemplate;

    @Autowired
    public ChatPublisherImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    @Override
    public void publish(ChatRequestInput request) throws IOException {
        // Send request to message service for create message
        URL messageUrl = new URL("http://localhost:8081/messages/insert");
        HttpURLConnection messageConnection = (HttpURLConnection) messageUrl.openConnection();
        messageConnection.setRequestMethod("POST");
        messageConnection.setRequestProperty("Content-Type", "application/json");
        messageConnection.setRequestProperty("Accept", "application/json");
        messageConnection.setDoOutput(true);
        String jsonInputString = "{\"text\": \"" + request.text() + "\"}";
        writeJsonToConnection(messageConnection , jsonInputString);

        int statusCode = messageConnection.getResponseCode();
        String messageId = null;
        if (statusCode == HttpURLConnection.HTTP_CREATED){
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            messageConnection.getInputStream()
                    )
            );
            messageId = bufferedReader.readLine();
            bufferedReader.close();
        }

        // Send request to user service for create message
        URL userUrl = new URL("http://localhost:8082/user/messages/insert");
        HttpURLConnection userFromConnection = (HttpURLConnection) userUrl.openConnection();
        userFromConnection.setRequestMethod("POST");
        userFromConnection.setRequestProperty("Content-Type", "application/json");
        userFromConnection.setRequestProperty("Accept", "application/json");
        userFromConnection.setDoOutput(true);
        String userFromJsonRequest = "{\"userId\":" + request.userIdFrom() + ", \"messageId\":\"" + messageId
                + "\",\"messageTypes\":[\"SEND_MESSAGE\"]}";
        writeJsonToConnection(userFromConnection , userFromJsonRequest);

        int statusCodeUserFrom = userFromConnection.getResponseCode();
        if (statusCodeUserFrom == HttpURLConnection.HTTP_CREATED){
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            userFromConnection.getInputStream()
                    )
            );
            bufferedReader.close();
        }else
            throw new IOException();

        HttpURLConnection userToConnection = (HttpURLConnection) userUrl.openConnection();
        userToConnection.setRequestMethod("POST");
        userToConnection.setRequestProperty("Content-Type", "application/json");
        userToConnection.setRequestProperty("Accept", "application/json");
        userToConnection.setDoOutput(true);
        String userToJsonRequest = "{\"userId\":" + request.userIdTo() + ", \"messageId\": \"" + messageId
                + "\",\"messageTypes\":[\"RECEIVED_MESSAGE\"]}";
        writeJsonToConnection(userToConnection , userToJsonRequest);

        int statusCodeUserTo = userToConnection.getResponseCode();
        if (statusCodeUserTo == HttpURLConnection.HTTP_CREATED){
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            userToConnection.getInputStream()
                    )
            );
            bufferedReader.close();
        }else
            throw new IOException();
    }

    private void writeJsonToConnection(HttpURLConnection httpURLConnection , String json)
            throws IOException{
        DataOutputStream dataOutputStreamTo = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStreamTo.writeBytes(json);
        dataOutputStreamTo.flush();
        dataOutputStreamTo.close();
    }
}
