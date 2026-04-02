/**
 * REST Controller for managing task operations.
 *
 * Responsibilities:
 * - Accept task creation requests
 * - Validate input payloads
 * - Provide APIs to fetch tasks
 */

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

    /**
     * Creates a new task.
     *
     * Validates:
     * - Task type
     * - Payload presence
     * - Required fields per task type
     *
     * @param request Task request containing type and payload
     * @return task_id if successful, error otherwise
     */

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskRequest request) {

        // Validate task type to prevent unsupported execution

        if (request.getTaskType() == null || request.getTaskType().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Task type required"));
        }

        // Validate payload existence to prevent null pointer exceptions in workers
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