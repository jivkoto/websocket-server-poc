package com.poc.websocket.server.wsjava.service;

import com.poc.websocket.server.wsjava.EmsWebSocketServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnProperty("wsj.enabled")
public class EmsWebSocketServerInitializer
{
    private final EmsWebSocketServer emsWebSocketServer;

    @PostConstruct
    public void init()
    {
        log.info("Starting ems web socket server");
        emsWebSocketServer.start();
    }
}
