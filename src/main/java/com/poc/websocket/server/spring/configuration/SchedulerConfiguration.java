package com.poc.websocket.server.spring.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfiguration
{
    @Bean
    public Executor scheduleExecutor()
    {
        return Executors.newScheduledThreadPool(5);
    }
}
