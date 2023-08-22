package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Zadania")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Zadania")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @Column(name = "Tresc_zadania")
    private String content;

    @Column(name = "Data_utworzenia")
    private LocalDateTime createdDate;

    @Column(name = "Priorytet")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "Status_zadania")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "Powiazane_tagi_albo_kategorie")
    @ElementCollection
    private Set<String> relatedTagsOrCategories;
}
