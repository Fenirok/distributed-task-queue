package com.aditya.distributed_task_queue.controller;

import com.aditya.distributed_task_queue.dto.TaskRequest;
import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody TaskRequest request) {

        UUID id = service.createTask(
                request.getTaskType(),
                request.getPayload()
        );

        return Map.of("task_id", id);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable UUID id) {
        return service.getTask(id);
    }
}