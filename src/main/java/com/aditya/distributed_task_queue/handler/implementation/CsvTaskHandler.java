package com.aditya.distributed_task_queue.handler.implementation;

import com.aditya.distributed_task_queue.handler.TaskHandler;
import com.aditya.distributed_task_queue.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component("csv_process")
public class CsvTaskHandler implements TaskHandler {

    /*
     * @Override
     * public void execute(Task task) throws Exception {
     * 
     * System.out.println("Starting CSV processing...");
     * System.out.println("Payload: " + task.getPayload());
     * 
     * // Simulate parsing CSV
     * Thread.sleep(1000);
     * System.out.println("Parsing CSV file...");
     * 
     * // Simulate validation
     * Thread.sleep(1000);
     * System.out.println("Validating data...");
     * 
     * // Simulate DB import
     * Thread.sleep(1000);
     * System.out.println("Importing data into database...");
     * 
     * System.out.println("CSV processing completed.");
     * }
     */

    // @Override
    // public void execute(Task task) throws Exception {

    // ObjectMapper mapper = new ObjectMapper();
    // Map<String, Object> payload = mapper.readValue(task.getPayload(), Map.class);

    // if (!payload.containsKey("fileName")) {
    // throw new RuntimeException("Missing fileName in CSV payload");
    // }

    // System.out.println("Processing CSV: " + payload.get("fileName"));

    // Thread.sleep(2000);
    // }

    @Override
    public void execute(Task task) throws Exception {

        System.out.println("CSV HANDLER EXECUTING...");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.readValue(task.getPayload(), Map.class);

        String fileName = (String) payload.get("fileName");

        // STRICT VALIDATION
        if (fileName == null || fileName.isBlank()) {
            throw new RuntimeException("Invalid CSV Payload: fileName missing or empty");
        }

        System.out.println("Processing CSV file: " + fileName);

        // simulate parsing
        Thread.sleep(1000);
        System.out.println("Parsing CSV...");

        // simulate validation
        Thread.sleep(1000);
        System.out.println("Validating data...");

        // simulate DB import
        Thread.sleep(1000);
        System.out.println("Importing data...");

        System.out.println("CSV processing completed.");
    }
}