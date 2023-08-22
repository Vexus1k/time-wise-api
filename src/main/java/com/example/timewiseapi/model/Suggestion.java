package com.example.timewiseapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Sugestie")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Sugestii")
    private int id;

    @Column(name = "Tresc_sugestii")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "Kategoria_sugestii")
    private SuggestionCategory suggestionCategory;
}
