package com.aditya.distributed_task_queue.handler;

import com.aditya.distributed_task_queue.model.Task;

public interface TaskHandler {
    void execute(Task task) throws Exception;
}