package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.TaskService;
import com.example.timewiseapi.model.Calendar;
import com.example.timewiseapi.model.Task;
import com.example.timewiseapi.model.projection.CalendarResponse;
import com.example.timewiseapi.model.projection.TaskResponse;
import com.example.timewiseapi.user.User;
import com.example.timewiseapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody Task task,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);

        Task createdTask = taskService.createTask(task, user);
        return ResponseEntity.ok(taskService.mapTaskToTaskResponse(createdTask));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TaskResponse>> getAllTasks(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);

        assert user != null;
        List<Task> tasks = taskService.getAllTasksForUser(user.getId());
        List<TaskResponse> tasksResponses = tasks.stream()
                .map(taskService::mapTaskToTaskResponse)
                .toList();

        return ResponseEntity.ok(tasksResponses);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable int taskId,
            @RequestBody Task updatedTask,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);

        Task updatedTaskWithUser = taskService.prepareTaskForUpdate(updatedTask, user);
        Optional<Task> updatedTaskOptional = taskService.updateTask(taskId, updatedTaskWithUser);

        if (updatedTaskOptional.isPresent()) {
            Task savedTask = updatedTaskOptional.get();
            return ResponseEntity.ok(taskService.mapTaskToTaskResponse(savedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
