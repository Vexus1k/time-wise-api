package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Notatki")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Notatki")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @Column(name = "Tresc_notatki")
    private String content;
}
