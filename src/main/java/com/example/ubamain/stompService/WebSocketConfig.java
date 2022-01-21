package com.example.ubamain.stompService;


import com.example.ubamain.dto.DataResponse;
import com.example.ubamain.service.UbaService;
import org.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.io.IOException;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final UbaService ubaService;

    public WebSocketConfig(UbaService ubaService) {
        this.ubaService = ubaService;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry
                                               registry) {
        registry.addEndpoint("/gs-guide-websocket");
//                .setAllowedOrigins("mydomain.com").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/fetch/");
        config.setApplicationDestinationPrefixes("/app");
    }

    @MessageMapping("/data")
    @SendTo("/fetch/data")
    public DataResponse broadcastNews(@Payload String message) throws JSONException, InterruptedException, IOException {
        Thread.sleep(1000);
        return ubaService.fetchData();
    }
}
