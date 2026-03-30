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

    /*
     * @PostMapping
     * public Map<String, Object> create(@RequestBody TaskRequest request) {
     * 
     * UUID id = service.createTask(
     * request.getTaskType(),
     * request.getPayload()
     * );
     * 
     * return Map.of("task_id", id);
     * }
     */

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskRequest request) {

        // VALIDATE TASK TYPE
        if (request.getTaskType() == null ||
                (!request.getTaskType().equals("email_send") &&
                        !request.getTaskType().equals("csv_process") &&
                        !request.getTaskType().equals("report_generation"))) {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Invalid task type"));
        }

        // VALIDATE PAYLOAD
        if (request.getPayload() == null || request.getPayload().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Payload cannot be empty"));
        }

        // EMAIL VALIDATION
        if (request.getTaskType().equals("email_send")) {
            if (!request.getPayload().containsKey("to") ||
                    !request.getPayload().containsKey("subject") ||
                    !request.getPayload().containsKey("body")) {

                return ResponseEntity
                        .badRequest()
                        .body(Map.of("error", "Missing fields: to, subject, body"));
            }
        }

        // CSV VALIDATION
        if (request.getTaskType().equals("csv_process")) {

            if (!request.getPayload().containsKey("fileName")) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("error", "Missing field: fileName"));
            }
        }

        // REPORT VALIDATION
        if (request.getTaskType().equals("report_generation")) {

            if (!request.getPayload().containsKey("reportType")) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("error", "Missing field: reportType"));
            }
        }

        UUID id = service.createTask(
                request.getTaskType(),
                request.getPayload());

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