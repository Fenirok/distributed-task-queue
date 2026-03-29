/*
package com.aditya.distributed_task_queue.service;

import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;

import tools.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public UUID createTask(String type, Object payload) {
        try {
            Task task = new Task();
            task.setId(UUID.randomUUID());
            task.setTaskType(type);
            task.setPayload(mapper.writeValueAsString(payload));
            task.setStatus(TaskStatus.PENDING);
            task.setCreatedAt(LocalDateTime.now());

            repo.save(task);
            return task.getId();

        } catch (Exception e) {
            throw new RuntimeException("Error creating task");
        }
    }

    public Task getTask(UUID id) {
        return repo.findById(id).orElseThrow();
    }
}
*/

package com.aditya.distributed_task_queue.service;

import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public UUID createTask(String type, Object payload) {
        try {
            Task task = new Task();
            task.setId(UUID.randomUUID());
            task.setTaskType(type);
            task.setPayload(mapper.writeValueAsString(payload));
            task.setStatus(TaskStatus.PENDING);
            task.setCreatedAt(LocalDateTime.now());

            repo.save(task);

            return task.getId();

        } catch (Exception e) {
            throw new RuntimeException("Error creating task", e);
        }
    }

    public Task getTask(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}