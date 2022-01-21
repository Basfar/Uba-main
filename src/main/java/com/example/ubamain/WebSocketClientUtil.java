package com.example.ubamain;


import com.example.ubamain.stompService.MyStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class WebSocketClientUtil {
    WebSocketClient client = new StandardWebSocketClient();

    WebSocketStompClient stompClient = new WebSocketStompClient(client);
     public void call(){
         stompClient.setMessageConverter(new MappingJackson2MessageConverter());
         StompSessionHandler sessionHandler = new MyStompSessionHandler();
         stompClient.connect("URL", sessionHandler);


     }

}
