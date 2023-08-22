package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Note;
import com.example.timewiseapi.model.NoteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlNoteRepository extends NoteRepository, JpaRepository<Note, Integer> {
}
