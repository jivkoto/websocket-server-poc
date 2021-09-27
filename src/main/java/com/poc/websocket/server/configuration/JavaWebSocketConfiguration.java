package com.poc.websocket.server.configuration;

import com.poc.websocket.server.service.InstanceService;
import com.poc.websocket.server.wsjava.service.JavaWebSocketInstanceCache;
import com.poc.websocket.server.wsjava.service.JavaWebSocketInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty("wsj.enabled")
public class JavaWebSocketConfiguration
{

    @Bean
    public JavaWebSocketInstanceCache instanceCache()
    {
        return new JavaWebSocketInstanceCache();
    }

    @Bean
    public InstanceService instanceService(JavaWebSocketInstanceCache cache)
    {
        log.info("Initializing JavaWebSocket as web socket implementation");
        return new JavaWebSocketInstanceService(cache);
    }
}
