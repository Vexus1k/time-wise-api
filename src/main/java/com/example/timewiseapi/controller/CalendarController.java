package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.CalendarService;
import com.example.timewiseapi.model.Calendar;
import com.example.timewiseapi.model.CalendarRepository;
import com.example.timewiseapi.model.projection.CalendarResponse;
import com.example.timewiseapi.user.User;
import com.example.timewiseapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarController {

    private final CalendarService calendarService;
    private final UserRepository userRepository;

    @GetMapping("/events")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalendarResponse>> getAllEvents(Authentication authentication) {
        String username = authentication.getName();
        Optional<User> user = userRepository.findByEmail(username);

        List<Calendar> events = calendarService.getEventsForUser(user.get().getId());
        List<CalendarResponse> eventResponses = events.stream()
                .map(calendarService::mapToCalendarResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventResponses);
    }

    @GetMapping("/events/notifications")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CalendarResponse>> getEventsWithNotifications(Authentication authentication) {
        String username = authentication.getName();
        Optional<User> user = userRepository.findByEmail(username);

        List<Calendar> eventsWithNotifications = calendarService.getEventsWithNotifications(user.get().getId());
        List<CalendarResponse> eventResponses = eventsWithNotifications.stream()
                .map(calendarService::mapToCalendarResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventResponses);
    }

    @DeleteMapping("/events/{eventId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteEvent(@PathVariable int eventId) {
        calendarService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CalendarResponse> addEvent(@RequestBody Calendar event, Authentication authentication) {
        String username = authentication.getName();
        Optional<User> user = userRepository.findByEmail(username);
        event.setUser(user.get());

        Calendar addedEvent = calendarService.addEventToCalendar(event);
        return ResponseEntity.ok(calendarService.mapToCalendarResponse(addedEvent));
    }

    @PatchMapping("/toggle-notification/{eventId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> toggleNotification(@PathVariable int eventId) {
        calendarService.toggleNotification(eventId);
        return ResponseEntity.ok().build();
    }
}
