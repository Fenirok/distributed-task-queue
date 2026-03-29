/*
package com.aditya.distributed_task_queue.worker;

import com.aditya.distributed_task_queue.handler.TaskHandlerFactory;
import com.aditya.distributed_task_queue.model.Task;
import com.aditya.distributed_task_queue.model.TaskStatus;
import com.aditya.distributed_task_queue.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskWorker {

    private final TaskRepository repo;
    private final TaskHandlerFactory factory;

    public TaskWorker(TaskRepository repo, TaskHandlerFactory factory) {
        this.repo = repo;
        this.factory = factory;
    }

    @PostConstruct
    public void start() {
        new Thread(this::run).start();
    }

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

    private void process(Task task) {

        try {
            task.setStatus(TaskStatus.RUNNING);
            task.setStartedAt(LocalDateTime.now());
            repo.save(task);

            factory.getHandler(task.getTaskType()).execute(task);

            task.setStatus(TaskStatus.COMPLETED);
            task.setResult("Success");

        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setError(e.getMessage());
        }

        task.setCompletedAt(LocalDateTime.now());
        repo.save(task);
    }
}
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

    private void process(Task task) {

        try {
            task.setStatus(TaskStatus.RUNNING);
            task.setStartedAt(LocalDateTime.now());
            repo.save(task);

            factory.getHandler(task.getTaskType()).execute(task);

            task.setStatus(TaskStatus.COMPLETED);
            task.setResult("Success");

        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setError(e.getMessage());
        }

        task.setCompletedAt(LocalDateTime.now());
        repo.save(task);
    }
}