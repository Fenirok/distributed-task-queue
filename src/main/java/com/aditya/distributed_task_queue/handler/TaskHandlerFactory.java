/**
 * Factory class to retrieve appropriate handler based on task type.
 *
 * Uses Spring's dependency injection to map task type → handler.
 */

package com.aditya.distributed_task_queue.handler;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskHandlerFactory {

    private final Map<String, TaskHandler> handlers;

    public TaskHandlerFactory(Map<String, TaskHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Returns handler for given task type.
     *
     * @param type Task type
     * @return TaskHandler implementation
     */
    public TaskHandler getHandler(String type) {
        TaskHandler handler = handlers.get(type);

        if (handler == null) {
            throw new RuntimeException("Invalid task type: " + type);
        }

        return handler;
    }
}