package com.aditya.distributed_task_queue.worker;

import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;
import com.aditya.distributed_task_queue.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskWorkerTest {

    @Autowired
    private TaskWorker worker;

    @Autowired
    private TaskRepository repo;

    @Autowired
    private TaskService service;

    @Test
    void shouldMarkTaskFailedOnException() {

        // Create task using SERVICE (correct way)
        Map<String, Object> payload = Map.of(
                "to", ""   // invalid → should fail in handler
        );

        UUID id = service.createTask("email_send", payload);

        Task task = repo.findById(id).orElseThrow();

        // Run worker
        worker.process(task);

        Task updated = repo.findById(id).orElseThrow();

        assertEquals(TaskStatus.FAILED, updated.getStatus());
    }
}