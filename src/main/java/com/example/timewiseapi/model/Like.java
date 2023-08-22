package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Polubienia")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_Wpisu", referencedColumnName = "ID_Wpisu")
    private Community post;
}
