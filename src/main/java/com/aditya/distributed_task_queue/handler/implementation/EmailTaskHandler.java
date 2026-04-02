/**
 * Handles email sending task.
 *
 * Simulates sending email by:
 * - Validating payload
 * - Printing logs
 * - Adding delay to mimic real-world processing
 */

package com.aditya.distributed_task_queue.handler.implementation;

import com.aditya.distributed_task_queue.handler.TaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component("email_send")
public class EmailTaskHandler implements TaskHandler {

    /*
     * @Override
     * public void execute(Task task) throws Exception {
     * System.out.println("Sending email: " + task.getPayload());
     * Thread.sleep(3000);
     * }
     */

    @Override
    public void execute(Task task) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.readValue(task.getPayload(), Map.class);

        // VALIDATION
        if (!payload.containsKey("to") ||
                !payload.containsKey("subject") ||
                !payload.containsKey("body")) {

            throw new RuntimeException("Invalid email payload");
        }

        System.out.println("Sending email to: " + payload.get("to"));

        Thread.sleep(3000);
    }
}