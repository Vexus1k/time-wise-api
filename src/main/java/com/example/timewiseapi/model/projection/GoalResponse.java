package com.example.timewiseapi.model.projection;

import com.example.timewiseapi.model.GoalStatus;
import com.example.timewiseapi.model.Progress;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalResponse {
    private int id;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    private GoalStatus status;
    private List<Progress> progressList = new ArrayList<>();
}
