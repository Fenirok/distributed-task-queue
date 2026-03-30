package com.aditya.distributed_task_queue.handler;

import com.aditya.distributed_task_queue.handler.implementation.ReportTaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ReportTaskHandlerTest {

    @Autowired
    private ReportTaskHandler handler;

    @Test
    void shouldFailWhenReportTypeMissing() {

        Task task = new Task();
        task.setPayload("""
        {
          "reportType": ""
        }
        """);

        assertThrows(RuntimeException.class, () -> {
            handler.execute(task);
        });
    }
}