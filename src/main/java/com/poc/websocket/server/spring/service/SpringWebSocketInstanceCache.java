package com.poc.websocket.server.spring.service;

import com.poc.websocket.server.service.InstanceCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
public class SpringWebSocketInstanceCache extends InstanceCache<WebSocketSession>
{
}
