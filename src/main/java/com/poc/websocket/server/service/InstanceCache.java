package com.poc.websocket.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public abstract class InstanceCache<T>
{
    private final ConcurrentHashMap<String, T> instances = new ConcurrentHashMap<>();

    public void addInstance(String instance, T session)
    {
        instances.put(instance, session);
    }

    public T getSession(String instance)
    {
        return instances.get(instance);
    }

    public void removeSession(T session)
    {
        if (instances.containsValue(session))
        {
            Optional<String> matchingKeyOptional =
                    instances.keySet().stream().filter(key -> session.equals(instances.get(key))).findFirst();
            if (matchingKeyOptional.isPresent())
            {
                String matchingKey = matchingKeyOptional.get();
                instances.remove(matchingKey);
                log.info("Will remove session:{} for key:{}", session, matchingKey);
            } else {
                log.error("No matching entry for session:{}", session);
            }
        }
    }

    public Set<String> getInstances()
    {
        return instances.keySet();
    }

}
