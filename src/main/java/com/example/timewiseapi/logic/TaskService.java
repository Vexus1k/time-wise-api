package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.Task;
import com.example.timewiseapi.model.TaskRepository;
import com.example.timewiseapi.model.projection.TaskResponse;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasksForUser(int userId) {
        return taskRepository.findByUserId(userId);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task prepareTaskForUpdate(Task updatedTask, User user) {
        updatedTask.setUser(user);
        return updatedTask;
    }

    public Optional<Task> updateTask(int taskId, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setContent(updatedTask.getContent());
            existingTask.setCreatedDate(updatedTask.getCreatedDate());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setRelatedTagsOrCategories(updatedTask.getRelatedTagsOrCategories());
            return Optional.of(taskRepository.save(existingTask));
        } else {
            return Optional.empty();
        }
    }

    public TaskResponse mapTaskToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .content(task.getContent())
                .createdDate(task.getCreatedDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .relatedTagsOrCategories(task.getRelatedTagsOrCategories())
                .build();
    }

}

