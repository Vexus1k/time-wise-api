package com.example.timewiseapi.model;

import java.util.List;

public interface SuggestionRepository {
    Suggestion save(Suggestion suggestion);

    List<Suggestion> findBySuggestionCategory(SuggestionCategory category);

    List<Suggestion> findAll();
}
