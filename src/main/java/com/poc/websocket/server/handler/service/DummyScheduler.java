package com.poc.websocket.server.handler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class DummyScheduler
{
    private final InstanceService instanceService;
    private final InstanceCache instanceCache;

    @Scheduled(fixedRate = 20000)
    public void sendDummyMessage()
    {
        log.info("in scheduler !");
        Set<String> instanceIds = instanceCache.getInstances();
        if (!instanceIds.isEmpty())
        {
            String randomKey = instanceIds.iterator().next();
            instanceService.sendMessage(randomKey, "Hello world");
        }
    }
}
