package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Kalendarz")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Wydarzenia")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @Column(name = "Tytul_wydarzenia")
    private String title;

    @Column(name = "Data_rozpoczecia")
    private LocalDateTime startingDate;

    @Column(name = "Data_zakonczenia")
    private LocalDateTime deadline;

    @Column(name = "Przypomnienie")
    private boolean addedNotification;

    @Column(name = "Opis")
    private String description;
}
