package com.example.timewiseapi.model;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository {
    Optional<Calendar> findById(int eventId);

    List<Calendar> findByUserId(int userId);

    Calendar save(Calendar event);

    List<Calendar> findByUserIdAndAddedNotificationTrue(int userId);

    void deleteById(int eventId);
}
