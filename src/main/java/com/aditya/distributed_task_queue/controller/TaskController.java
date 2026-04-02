package com.aditya.distributed_task_queue.controller;

import com.aditya.distributed_task_queue.dto.TaskRequest;
import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.service.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> create(@RequestBody TaskRequest request) {

        // ✅ ONLY minimal validation (DO NOT BLOCK EXECUTION)

        if (request.getTaskType() == null || request.getTaskType().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Task type required"));
        }

        if (request.getPayload() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Payload required"));
        }

        // ❌ REMOVE ALL DEEP VALIDATIONS (email, csv, report)
        // Let worker handle failures

        UUID id = service.createTask(
                request.getTaskType(),
                request.getPayload()
        );

        return ResponseEntity.ok(Map.of("task_id", id));
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable UUID id) {
        return service.getTask(id);
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(required = false) TaskStatus status) {

        if (status != null) {
            return service.getTasksByStatus(status);
        }

        return service.getAllTasks();
    }
}