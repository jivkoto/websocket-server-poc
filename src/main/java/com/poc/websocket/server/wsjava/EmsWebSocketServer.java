package com.poc.websocket.server.wsjava;

import com.poc.websocket.server.service.InstanceService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

@Slf4j
public class EmsWebSocketServer extends WebSocketServer
{
    private final InstanceService<WebSocket, String> instanceService;

    public EmsWebSocketServer(InetSocketAddress address, InstanceService<WebSocket, String> instanceService)
    {
        super(address);
        this.instanceService = instanceService;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake)
    {
        log.info("socket:{} handshake:{}", webSocket, clientHandshake);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b)
    {
        log.info("Closing:{}", webSocket);
        instanceService.removeSession(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String textMessage)
    {
        log.info("Text message:{} from connection:{}", textMessage, webSocket);
        instanceService.processMessage(webSocket, textMessage);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message)
    {
        log.info("Byte message:{} from connection:{}, message, conn");
        super.onMessage(conn, message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e)
    {
        log.error("Error occurred on:{} ", webSocket);
        log.error("Error:", e);
    }

    @Override
    public void onStart()
    {
        log.info("WS server started");
    }
}
