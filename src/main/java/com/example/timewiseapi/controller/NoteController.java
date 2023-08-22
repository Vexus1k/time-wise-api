package com.example.timewiseapi.controller;

import com.example.timewiseapi.logic.NoteService;
import com.example.timewiseapi.model.Note;
import com.example.timewiseapi.model.projection.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<NoteResponse>> getAllNotes(Authentication authentication) {
        String userEmail = authentication.getName();
        List<Note> notes = noteService.getAllNotesForUser(userEmail);
        List<NoteResponse> noteResponses = notes.stream()
                .map(noteService::mapToNoteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(noteResponses);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<NoteResponse> createNote(
            @RequestBody Note note,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        Note createdNote = noteService.createNoteForUser(userEmail, note);
        return ResponseEntity.ok(noteService.mapToNoteResponse(createdNote));
    }

    @DeleteMapping("/{noteId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNote(
            @PathVariable int noteId
    ) {
        noteService.deleteNoteById(noteId);
        return ResponseEntity.noContent().build();
    }
}

