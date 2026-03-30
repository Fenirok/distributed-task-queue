package com.aditya.distributed_task_queue.handler.implementation;

import com.aditya.distributed_task_queue.handler.TaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component("report_generation")
public class ReportTaskHandler implements TaskHandler {

    /*
     * @Override
     * public void execute(Task task) {
     * System.out.println("Generating report...");
     * }
     */

    /*
     * @Override
     * public void execute(Task task) throws Exception {
     * 
     * ObjectMapper mapper = new ObjectMapper();
     * Map<String, Object> payload = mapper.readValue(task.getPayload(), Map.class);
     * 
     * if (!payload.containsKey("reportType")) {
     * throw new RuntimeException("Missing reportType");
     * }
     * 
     * System.out.println("Generating report...");
     * 
     * Thread.sleep(2000);
     * }
     */

    @Override
    public void execute(Task task) throws Exception {

        System.out.println("REPORT HANDLER EXECUTING...");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.readValue(task.getPayload(), Map.class);

        String reportType = (String) payload.get("reportType");

        if (reportType == null || reportType.isBlank()) {
            throw new RuntimeException("Invalid Report Payload: reportType missing");
        }

        System.out.println("Generating report: " + reportType);

        Thread.sleep(2000);

        System.out.println("Report generated successfully.");
    }
}