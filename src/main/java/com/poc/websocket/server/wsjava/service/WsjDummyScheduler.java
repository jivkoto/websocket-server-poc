package com.poc.websocket.server.wsjava.service;

import com.poc.websocket.server.wsjava.EmsWebSocketServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnProperty("wsj.enabled")
public class WsjDummyScheduler
{
    private final EmsWebSocketServer emsWebSocketServer;

    @Scheduled(fixedRate = 20000)
    public void sendDummyMessage()
    {
        log.info("Sending broadcast ....");
        emsWebSocketServer.broadcast("Hello world".getBytes());
    }
}
