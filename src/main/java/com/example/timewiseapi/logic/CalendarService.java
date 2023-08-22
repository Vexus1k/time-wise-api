package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.Calendar;
import com.example.timewiseapi.model.CalendarRepository;
import com.example.timewiseapi.model.projection.CalendarResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public Calendar addEventToCalendar(Calendar event) {
        return calendarRepository.save(event);
    }

    public List<Calendar> getEventsWithNotifications(int userId) {
        return calendarRepository.findByUserIdAndAddedNotificationTrue(userId);
    }

    public void toggleNotification(int eventId) {
        Calendar event = calendarRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        event.setAddedNotification(!event.isAddedNotification());
        calendarRepository.save(event);
    }

    public void deleteEvent(int eventId) {
        calendarRepository.deleteById(eventId);
    }

    public List<Calendar> getEventsForUser(int userId) {
        return calendarRepository.findByUserId(userId);
    }

    public CalendarResponse mapToCalendarResponse(Calendar calendar) {
        return CalendarResponse.builder()
                .id(calendar.getId())
                .title(calendar.getTitle())
                .startingDate(calendar.getStartingDate())
                .deadline(calendar.getDeadline())
                .addedNotification(calendar.isAddedNotification())
                .description(calendar.getDescription())
                .build();
    }
}
