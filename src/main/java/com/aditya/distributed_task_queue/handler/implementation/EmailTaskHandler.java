package com.aditya.distributed_task_queue.handler.implementation;

import com.aditya.distributed_task_queue.handler.TaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import org.springframework.stereotype.Component;

@Component("email_send")
public class EmailTaskHandler implements TaskHandler {

    @Override
    public void execute(Task task) throws Exception {
        System.out.println("Sending email: " + task.getPayload());
        Thread.sleep(3000);
    }
}