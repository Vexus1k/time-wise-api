package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;

import java.util.List;

public interface NoteRepository {
    Note save(Note note);

    void deleteById(int noteId);

    List<Note> findByUser(User user);
}
