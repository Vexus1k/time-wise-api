package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Komentarze")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Komentarza")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_Wpisu", referencedColumnName = "ID_Wpisu")
    private Community community;

    @Column(name = "Tresc")
    private String content;

    @Column(name = "Data_dodania")
    private LocalDateTime commentDate;
}
