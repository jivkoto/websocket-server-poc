package com.poc.websocket.server.spring.configuration;

import com.poc.websocket.server.spring.handler.EmsHandler;
import com.poc.websocket.server.spring.service.InstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{

    private final InstanceService instanceService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        registry.addHandler(emsHandler(instanceService), "/ems");
    }

    @Bean
    public EmsHandler emsHandler(InstanceService instanceService)
    {
        return new EmsHandler(instanceService);
    }
}
