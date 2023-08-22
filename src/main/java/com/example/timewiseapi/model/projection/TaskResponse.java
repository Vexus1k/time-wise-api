package com.example.timewiseapi.model.projection;

import com.example.timewiseapi.model.TaskPriority;
import com.example.timewiseapi.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private int id;
    private String content;
    private LocalDateTime createdDate;
    private TaskPriority priority;
    private TaskStatus status;
    private Set<String> relatedTagsOrCategories;
}
