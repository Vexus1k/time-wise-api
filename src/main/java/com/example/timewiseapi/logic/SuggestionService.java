package com.example.timewiseapi.logic;

import com.example.timewiseapi.model.Suggestion;
import com.example.timewiseapi.model.SuggestionCategory;
import com.example.timewiseapi.model.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    public Suggestion addSuggestion(Suggestion suggestion) {
        return suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }

    public List<Suggestion> getSuggestionsByCategory(SuggestionCategory category) {
        return suggestionRepository.findBySuggestionCategory(category);
    }
}
