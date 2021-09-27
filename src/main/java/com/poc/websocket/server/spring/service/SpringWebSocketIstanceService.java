package com.poc.websocket.server.spring.service;

import com.google.gson.Gson;
import com.poc.websocket.server.service.InstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class SpringWebSocketIstanceService implements InstanceService<WebSocketSession, TextMessage>
{
    private final String TYPE = "SpringWebSocket";

    private final SpringWebSocketInstanceCache instanceCache;

    @Override
    public void processMessage(WebSocketSession session, TextMessage message)
    {
        if (isSubscribeMessage(message))
        {
            processAsSubscribeMessage(session, message);
        }
    }

    @Override
    public void removeSession(WebSocketSession session)
    {
        instanceCache.removeSession(session);
    }

    @Override
    public void sendMessage(String instance, String message)
    {
        WebSocketSession session = instanceCache.getSession(instance);
        if (session != null)
        {
            try
            {
                session.sendMessage(new BinaryMessage(message.getBytes()));
                log.info("Send message:{} to instance:{}", message, instance);
            } catch (IOException ioe)
            {
                log.error("Error while sending message:{} to instance:{}", message, instance);
                log.error("Error:", ioe);
            }
        } else {
            log.error("No connection to instance:{} was found", instance);
        }
    }

    private boolean isSubscribeMessage(TextMessage message)
    {
        return (message.getPayload().contains(SUBSCRIBE_TYPE));
    }

    private void processAsSubscribeMessage(WebSocketSession session, TextMessage textMessage)
    {
        String message = textMessage.getPayload();
        Gson gson = new Gson();
        Map<String, String> subscribeMap = gson.fromJson(message, Map.class);
        String identifier = subscribeMap.get(IDENTIFIER_KEY);
        if (identifier != null)
        {
            log.info("Processing subscription for identifier:{}", identifier);
            instanceCache.addInstance(identifier, session);
        } else
        {
            log.error("Can't find identifier from subscription message:{}", message);
        }
    }

    @Override
    public String getType()
    {
        return TYPE;
    }
}
