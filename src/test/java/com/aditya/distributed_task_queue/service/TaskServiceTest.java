package com.aditya.distributed_task_queue.service;

import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskRepository repo;

    @Test
    void shouldCreateTaskWithPendingStatus() {

        Map<String, Object> payload = Map.of(
                "to", "test@gmail.com",
                "subject", "Hello",
                "body", "Test"
        );

        UUID id = service.createTask("email_send", payload);

        Task task = repo.findById(id).orElseThrow();

        assertEquals(TaskStatus.PENDING, task.getStatus());
    }
}