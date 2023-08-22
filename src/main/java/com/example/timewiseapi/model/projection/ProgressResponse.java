package com.example.timewiseapi.model.projection;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressResponse {
    private int id;
    private int userId;
    private int goalId;
    private LocalDateTime date;
    private String description;
}
