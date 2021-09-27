package com.poc.websocket.server.wsjava.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("wsj")
public class JavaWebSocketProperties
{
    private String enabled;
    private String host = "localhost";
    private int port = 8081;
}
