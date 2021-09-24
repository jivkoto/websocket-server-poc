package com.poc.websocket.server.wsjava.configuration;

import com.poc.websocket.server.wsjava.EmsWebSocketServer;
import com.poc.websocket.server.wsjava.configuration.properties.JavaWebSocketProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@ConditionalOnProperty("wsj.enabled")
public class WsjEmsServerConfiguration
{
    @Bean
    public EmsWebSocketServer emsWebSocketServer(JavaWebSocketProperties config)
    {
        EmsWebSocketServer server =  new EmsWebSocketServer(new InetSocketAddress(config.getHost(), config.getPort()));
        // disable pong
        server.setConnectionLostTimeout(0);
        return server;
    }
}
