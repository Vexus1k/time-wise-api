DROP TABLE if EXISTS Notatki;

CREATE TABLE Notatki (
    ID_Notatki INT AUTO_INCREMENT PRIMARY KEY,
    ID_Uzytkownika INT,
    Tresc_notatki TEXT,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);