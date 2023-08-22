package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.ProgressService;
import com.example.timewiseapi.model.Goal;
import com.example.timewiseapi.model.Progress;
import com.example.timewiseapi.model.projection.ProgressRequest;
import com.example.timewiseapi.model.projection.ProgressResponse;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ProgressResponse>> getAllProgress(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Progress> progressList = progressService.getProgressForUser(user);
        List<ProgressResponse> progressResponses = progressList.stream()
                .map(progressService::mapProgressToProgressResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(progressResponses);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ProgressResponse> addProgress(
            @RequestBody ProgressRequest progressRequest,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        Progress progress = progressService.addProgress(user, progressRequest);

        if (progress != null) {
            ProgressResponse progressResponse = progressService.mapProgressToProgressResponse(progress);
            return ResponseEntity.ok(progressResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{progressId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProgressById(@PathVariable int progressId) {
        progressService.deleteProgressById(progressId);
        return ResponseEntity.noContent().build();
    }
}
