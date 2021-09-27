package com.poc.websocket.server.spring.configuration;

import com.poc.websocket.server.spring.handler.EmsHandler;
import com.poc.websocket.server.spring.service.SpringWebSocketIstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSocket
@ConditionalOnBean(SpringWebSocketIstanceService.class)
public class SpringWebSocketConfig implements WebSocketConfigurer
{

    private final SpringWebSocketIstanceService instanceService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        log.info("Registering web socket handlers");
        registry.addHandler(emsHandler(instanceService), "/ems");
    }

    @Bean
    public EmsHandler emsHandler(SpringWebSocketIstanceService instanceService)
    {
        return new EmsHandler(instanceService);
    }
}
