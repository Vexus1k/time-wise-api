DROP TABLE if EXISTS Tokeny;

CREATE TABLE Tokeny (
    ID_Tokenu INT PRIMARY KEY AUTO_INCREMENT,
    Token_dostepu VARCHAR(255) UNIQUE,
    Rodzaj_tokenu VARCHAR(10),
    Wycofany BOOLEAN,
    Wygasniety BOOLEAN,
    Uzytkownik_ID INT,
    Data_wygasniecia DATETIME,
    FOREIGN KEY (Uzytkownik_ID) REFERENCES Uzytkownicy (ID_Uzytkownika)
);