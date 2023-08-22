package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.GoalService;
import com.example.timewiseapi.model.Goal;
import com.example.timewiseapi.model.projection.GoalResponse;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<GoalResponse> createGoal(
            @RequestBody Goal newGoal,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        Goal createdGoal = goalService.createGoal(user, newGoal);
        GoalResponse goalResponse = goalService.mapGoalToGoalResponse(createdGoal);
        return ResponseEntity.ok(goalResponse);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<GoalResponse>> getAllGoals(Authentication authentication) {
        List<GoalResponse> goalResponses = goalService.getAllGoalsWithProgressAndMap();
        return ResponseEntity.ok(goalResponses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<GoalResponse> updateGoal(
            @PathVariable Integer id,
            @RequestBody Goal updatedGoal,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        Goal existingGoal = goalService.getGoalByIdAndUser(id, user);

        if (existingGoal == null) {
            return ResponseEntity.notFound().build();
        }

        existingGoal.setContent(updatedGoal.getContent());
        existingGoal.setDeadline(updatedGoal.getDeadline());
        existingGoal.setCreatedDate(updatedGoal.getCreatedDate());
        existingGoal.setStatus(updatedGoal.getStatus());

        Goal updatedGoalObj = goalService.updateGoal(existingGoal);
        GoalResponse goalResponse = goalService.mapGoalToGoalResponse(updatedGoalObj);

        return ResponseEntity.ok(goalResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Integer id
    ) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
