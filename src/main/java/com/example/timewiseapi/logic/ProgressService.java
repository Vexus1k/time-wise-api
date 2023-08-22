package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.Goal;
import com.example.timewiseapi.model.GoalRepository;
import com.example.timewiseapi.model.Progress;
import com.example.timewiseapi.model.ProgressRepository;
import com.example.timewiseapi.model.projection.ProgressRequest;
import com.example.timewiseapi.model.projection.ProgressResponse;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final GoalRepository goalRepository;

    public Progress addProgress(User user, ProgressRequest progressRequest) {
        Goal goal = goalRepository.findById(progressRequest.getGoalId()).orElse(null);
        if (goal == null) {
            return null;
        }

        Progress progress = new Progress();
        progress.setUser(user);
        progress.setGoal(goal);
        progress.setDate(progressRequest.getDate());
        progress.setDescription(progressRequest.getDescription());

        return progressRepository.save(progress);
    }

    public List<Progress> getProgressForUser(User user) {
        return progressRepository.findByUser(user);
    }

    public void deleteProgressById(int progressId) {
        progressRepository.deleteById(progressId);
    }

    public ProgressResponse mapProgressToProgressResponse(Progress progress) {
        ProgressResponse response = new ProgressResponse();
        response.setId(progress.getId());
        response.setUserId(progress.getUser().getId());
        response.setGoalId(progress.getGoal().getId());
        response.setDate(progress.getDate());
        response.setDescription(progress.getDescription());
        return response;
    }
}
