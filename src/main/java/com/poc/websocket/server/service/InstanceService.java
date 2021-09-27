package com.poc.websocket.server.service;

public interface InstanceService<T, M>
{
    String IDENTIFIER_KEY = "identifier";
    String SUBSCRIBE_TYPE = "SUBSCRIBE";

    void processMessage(T session, M message);
    void removeSession(T session);
    void sendMessage(String instance, String message);
    String getType();
}
