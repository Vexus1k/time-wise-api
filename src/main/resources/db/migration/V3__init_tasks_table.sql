DROP TABLE if EXISTS Zadania;

CREATE TABLE Zadania (
    ID_Zadania INT PRIMARY KEY AUTO_INCREMENT,
    ID_Uzytkownika INT,
    Tresc_zadania TEXT,
    Data_utworzenia DATETIME,
    Priorytet VARCHAR(50),
    Status_zadania VARCHAR(50),
    Powiazane_tagi_albo_kategorie TEXT,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);
