package com.example.timewiseapi.token;

import com.example.timewiseapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tokeny")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_Tokenu")
  private Integer id;

  @Column(name = "Token_dostepu", unique = true)
  private String token;

  @Column(name = "Rodzaj_tokenu")
  @Enumerated(EnumType.STRING)
  private TokenType tokenType = TokenType.BEARER;

  @Column(name = "Wycofany")
  private boolean revoked;

  @Column(name = "Wygasniety")
  private boolean expired;

  @Column(name = "Data_wygasniecia")
  private LocalDateTime expiredDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Uzytkownik_ID")
  private User user;
}
