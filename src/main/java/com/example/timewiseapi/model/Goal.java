package com.example.timewiseapi.model;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cele")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Celu")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Uzytkownika", referencedColumnName = "ID_Uzytkownika")
    private User user;

    @Column(name = "Tresc_celu")
    private String content;

    @Column(name = "Data_utworzenia")
    private LocalDateTime createdDate;

    @Column(name = "Termin_wykonania")
    private LocalDateTime deadline;

    @Column(name = "Status_celu")
    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    @Transient
    private List<Progress> progressList = new ArrayList<>();
}
