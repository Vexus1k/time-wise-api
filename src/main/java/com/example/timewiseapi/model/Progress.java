package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Postepy")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Postepu")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_Celu", referencedColumnName = "ID_Celu")
    private Goal goal;

    @Column(name = "Data_wpisu")
    private LocalDateTime date;

    @Column(name = "Opis_postepow")
    private String description;
}
