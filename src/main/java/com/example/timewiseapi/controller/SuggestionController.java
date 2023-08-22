package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.SuggestionService;
import com.example.timewiseapi.model.Suggestion;
import com.example.timewiseapi.model.SuggestionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping
    public ResponseEntity<Suggestion> addSuggestion(@RequestBody Suggestion suggestion) {
        Suggestion addedSuggestion = suggestionService.addSuggestion(suggestion);
        return ResponseEntity.ok(addedSuggestion);
    }

    @GetMapping
    public ResponseEntity<List<Suggestion>> getAllSuggestions() {
        List<Suggestion> suggestions = suggestionService.getAllSuggestions();
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Suggestion>> getSuggestionsByCategory(
            @PathVariable SuggestionCategory category) {
        List<Suggestion> suggestions = suggestionService.getSuggestionsByCategory(category);
        return ResponseEntity.ok(suggestions);
    }
}
