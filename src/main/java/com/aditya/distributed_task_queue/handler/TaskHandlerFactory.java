package com.aditya.distributed_task_queue.handler;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskHandlerFactory {

    private final Map<String, TaskHandler> handlers;

    public TaskHandlerFactory(Map<String, TaskHandler> handlers) {
        this.handlers = handlers;
    }

    public TaskHandler getHandler(String type) {
        TaskHandler handler = handlers.get(type);
        if (handler == null) {
            throw new RuntimeException("No handler for type: " + type);
        }
        return handler;
    }
}