package com.poc.websocket.server.wsjava.service;

import com.google.gson.Gson;
import com.poc.websocket.server.service.InstanceCache;
import com.poc.websocket.server.service.InstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JavaWebSocketInstanceService implements InstanceService<WebSocket, String>
{

    private static final String TYPE = "JavaWebSocket";

    private final InstanceCache<WebSocket> instanceCache;

    @Override
    public void processMessage(WebSocket session, String message)
    {
        if (isSubscribeMessage(message))
        {
            processAsSubscribeMessage(session, message);
        }
    }

    @Override
    public void removeSession(WebSocket session)
    {
        instanceCache.removeSession(session);
    }

    @Override
    public void sendMessage(String instance, String message)
    {
        WebSocket session = instanceCache.getSession(instance);
        if (session != null)
        {
            session.send(message.getBytes());
            log.info("Send message:{} to instance:{}", message, instance);
        } else {
            log.error("No connection to instance:{} was found", instance);
        }
    }

    private boolean isSubscribeMessage(String message)
    {
        return (message.contains(SUBSCRIBE_TYPE));
    }

    private void processAsSubscribeMessage(WebSocket session, String textMessage)
    {

        Gson gson = new Gson();
        Map<String, String> subscribeMap = gson.fromJson(textMessage, Map.class);
        String identifier = subscribeMap.get(IDENTIFIER_KEY);
        if (identifier != null)
        {
            log.info("Processing subscription for identifier:{}", identifier);
            instanceCache.addInstance(identifier, session);
        } else
        {
            log.error("Can't find identifier from subscription message:{}", textMessage);
        }
    }

    @Override
    public String getType()
    {
        return TYPE;
    }

}
