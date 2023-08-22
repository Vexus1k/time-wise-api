package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Udostepnienia")
public class Sharing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Udostepnienia")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_Wpisu_Udostepnionego")
    private Community sharedPost;

    @Column(name = "Data_udostepnienia")
    LocalDateTime sharingDate;
}

