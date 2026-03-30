package com.aditya.distributed_task_queue.handler;

import com.aditya.distributed_task_queue.handler.implementation.EmailTaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailTaskHandlerTest {

    private final EmailTaskHandler handler = new EmailTaskHandler();

    @Test
    void shouldThrowExceptionWhenKeysMissing() {

        Task task = new Task();
        task.setPayload("""
        {
          "to": "test@gmail.com"
        }
        """);

        assertThrows(RuntimeException.class, () -> {
            handler.execute(task);
        });
    }

    @Test
    void shouldThrowExceptionForInvalidPayload() {

        Task task = new Task();
        task.setPayload("""
        {
          "wrong": "data"
        }
        """);

        assertThrows(RuntimeException.class, () -> {
            handler.execute(task);
        });
    }

    @Test
    void shouldPassForValidPayload() {

        Task task = new Task();
        task.setPayload("""
        {
          "to": "test@gmail.com",
          "subject": "Hello",
          "body": "Test"
        }
        """);

        assertDoesNotThrow(() -> {
            handler.execute(task);
        });
    }

    @Test
    void shouldPassEvenIfValuesAreEmptyBecauseOnlyKeysChecked() {

        Task task = new Task();
        task.setPayload("""
        {
          "to": "",
          "subject": "",
          "body": ""
        }
        """);

        assertDoesNotThrow(() -> {
            handler.execute(task);
        });
    }
}