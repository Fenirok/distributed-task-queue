package com.aditya.distributed_task_queue.handler.implementation;

import com.aditya.distributed_task_queue.handler.TaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import org.springframework.stereotype.Component;

@Component("report_generation")
public class ReportTaskHandler implements TaskHandler {

    @Override
    public void execute(Task task) {
        System.out.println("Generating report...");
    }
}