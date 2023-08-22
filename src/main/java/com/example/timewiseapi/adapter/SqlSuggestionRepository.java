package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Suggestion;
import com.example.timewiseapi.model.SuggestionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlSuggestionRepository extends SuggestionRepository, JpaRepository<Suggestion, Integer> {
}
