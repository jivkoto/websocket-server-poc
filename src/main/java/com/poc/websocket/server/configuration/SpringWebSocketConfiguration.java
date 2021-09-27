package com.poc.websocket.server.configuration;

import com.poc.websocket.server.service.InstanceService;
import com.poc.websocket.server.spring.service.SpringWebSocketInstanceCache;
import com.poc.websocket.server.spring.service.SpringWebSocketIstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnMissingBean(InstanceService.class)
public class SpringWebSocketConfiguration
{
    @Bean
    public SpringWebSocketInstanceCache instanceCache()
    {
        return new SpringWebSocketInstanceCache();
    }

    @Bean
    public SpringWebSocketIstanceService instanceService(SpringWebSocketInstanceCache cache)
    {
        log.info("Initializing Spring web socket as web socket implementation");
        return new SpringWebSocketIstanceService(cache);
    }
}
