package com.aditya.distributed_task_queue.handler;

import com.aditya.distributed_task_queue.handler.implementation.CsvTaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CsvTaskHandlerTest {

    @Autowired
    private CsvTaskHandler handler;

    @Test
    void shouldFailWhenFileNameMissing() {

        Task task = new Task();
        task.setPayload("""
        {
          "fileName": ""
        }
        """);

        assertThrows(RuntimeException.class, () -> {
            handler.execute(task);
        });
    }
}