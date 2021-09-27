package com.poc.websocket.server.wsjava.configuration;

import com.poc.websocket.server.service.InstanceService;
import com.poc.websocket.server.wsjava.EmsWebSocketServer;
import com.poc.websocket.server.wsjava.configuration.properties.JavaWebSocketProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@ConditionalOnProperty("wsj.enabled")
public class JavaWebSocketServerConfiguration
{
    @Bean
    public EmsWebSocketServer emsWebSocketServer(JavaWebSocketProperties config, InstanceService instanceService)
    {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(config.getHost(), config.getPort());
        EmsWebSocketServer server =  new EmsWebSocketServer(inetSocketAddress, instanceService);
        // disable pong
        server.setConnectionLostTimeout(0);
        return server;
    }
}
