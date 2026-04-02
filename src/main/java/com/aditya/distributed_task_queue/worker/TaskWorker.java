/**
 * Background worker responsible for processing tasks.
 *
 * Workflow:
 * - Poll database for PENDING tasks
 * - Mark task as RUNNING
 * - Execute using appropriate handler
 * - Update status to COMPLETED or FAILED
 *
 * Runs continuously in a separate thread.
 */

package com.aditya.distributed_task_queue.worker;

import com.aditya.distributed_task_queue.handler.TaskHandlerFactory;
import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskWorker {

    private final TaskRepository repo;
    private final TaskHandlerFactory factory;

    @PostConstruct
    public void start() {
        new Thread(this::run).start();
    }

    /**
     * Main loop:
     * - Polls DB every 2 seconds
     * - Processes all pending tasks
     */

    public void run() {
        while (true) {
            try {

                List<Task> tasks = repo.findByStatus(TaskStatus.PENDING);

                for (Task task : tasks) {
                    process(task);
                }

                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void process(Task task) {

        try {
            task.setStatus(TaskStatus.RUNNING);
            task.setStartedAt(LocalDateTime.now());
            repo.save(task);

            factory.getHandler(task.getTaskType()).execute(task);

            task.setStatus(TaskStatus.COMPLETED);
            task.setCompletedAt(LocalDateTime.now());

            long duration = java.time.Duration
                    .between(task.getStartedAt(), task.getCompletedAt())
                    .toMillis();

            task.setResult("Success (Execution Time: " + duration + " ms)");

        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setError("Error: " + e.getMessage());
        }

        repo.save(task);
    }
}