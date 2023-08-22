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
public class CalendarResponse {
    private int id;
    private String title;
    private LocalDateTime startingDate;
    private LocalDateTime deadline;
    private boolean addedNotification;
    private String description;
}
