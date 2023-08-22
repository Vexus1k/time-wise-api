package com.example.timewiseapi.user;

import com.example.timewiseapi.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Uzytkownicy")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_Uzytkownika")
  @Getter
  @Setter
  private int id;

  @Column(name = "Imie")
  @Getter
  @Setter
  private String firstName;

  @Column(name = "Nazwisko")
  @Getter
  @Setter
  private String lastName;

  @Column(name = "Adres_email")
  @Getter
  @Setter
  private String email;

  @Column(name = "Numer_telefonu")
  @Getter
  @Setter
  private String phoneNumber;

  @Column(name = "Haslo")
  @Setter
  private String password;

  @Column(name = "Data_rejestracji")
  @Getter
  @Setter
  private LocalDateTime registrationDate;

  @Getter
  @Setter
  @Column(name = "Aktywowany")
  private Boolean activated;

  @Getter
  @Setter
  @Column(name = "Token_aktywacyjny")
  private String activationToken;

  @Getter
  @Setter
  @Column(name = "Rola")
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
