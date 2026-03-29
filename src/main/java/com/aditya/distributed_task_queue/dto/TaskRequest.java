/*
package com.aditya.distributed_task_queue.dto;

import java.util.Map;

public class TaskRequest {

    private String taskType;
    private Map<String, Object> payload;

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}
*/

package com.aditya.distributed_task_queue.dto;

import lombok.Data;
import java.util.Map;

@Data
public class TaskRequest {
    private String taskType;
    private Map<String, Object> payload;
}