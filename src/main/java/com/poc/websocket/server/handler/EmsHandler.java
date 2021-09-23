package com.poc.websocket.server.handler;

import com.poc.websocket.server.service.InstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@RequiredArgsConstructor
@Slf4j
public class EmsHandler extends AbstractWebSocketHandler
{
    private final InstanceService instanceService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        log.info("session:{}", session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
    {
        log.info("session:{} text message:{}", session, message.getPayload());
        instanceService.processMessage(session, message);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception
    {
        log.info("session:{} binary message:{}", session, message);
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception
    {
        log.info("session:{} pong message:{}", session, message);
        super.handlePongMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
    {
        log.info("Connection closed for session:{} with status:{}", session, status);
        instanceService.removeSession(session);
    }
}
