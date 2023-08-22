package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.Goal;
import com.example.timewiseapi.model.GoalRepository;
import com.example.timewiseapi.model.Progress;
import com.example.timewiseapi.model.ProgressRepository;
import com.example.timewiseapi.model.projection.GoalResponse;
import com.example.timewiseapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final ProgressRepository progressRepository;

    public Goal createGoal(User user, Goal newGoal) {
        newGoal.setUser(user);
        return goalRepository.save(newGoal);
    }

    public List<GoalResponse> getAllGoalsWithProgressAndMap() {
        List<Goal> goals = goalRepository.findAll();
        List<Progress> progressList = progressRepository.findAll();

        List<GoalResponse> goalResponses = new ArrayList<>();
        for (Goal goal : goals) {
            List<Progress> filteredProgress = progressList.stream()
                    .filter(progress -> progress.getGoal().getId() == goal.getId())
                    .collect(Collectors.toList());

            goalResponses.add(mapGoalToGoalResponse(goal, filteredProgress));
        }

        return goalResponses;
    }

    public Goal updateGoal(Goal updatedGoal) {
        return goalRepository.save(updatedGoal);
    }

    public Goal getGoalByIdAndUser(Integer id, User user) {
        return goalRepository.findByIdAndUser(id, user);
    }

    public void deleteGoal(int goalId) {
        Optional<Goal> goal = goalRepository.findById(goalId);
        if (goal.isPresent()) {
            List<Progress> progressList = progressRepository.findByGoalId(goalId);
            for (Progress progress : progressList) {
                progressRepository.delete(progress);
            }
            goalRepository.delete(goal.get());
        }
    }
    
    public GoalResponse mapGoalToGoalResponse(Goal goal, List<Progress> progressList) {
        if (progressList == null) {
            progressList = new ArrayList<>();
        }

        GoalResponse.GoalResponseBuilder builder = GoalResponse.builder()
                .id(goal.getId())
                .content(goal.getContent())
                .createdDate(goal.getCreatedDate())
                .deadline(goal.getDeadline())
                .status(goal.getStatus())
                .progressList(progressList);

        return builder.build();
    }

    public GoalResponse mapGoalToGoalResponse(Goal goal) {
        return mapGoalToGoalResponse(goal, new ArrayList<>());
    }
}
