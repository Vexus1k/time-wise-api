DROP TABLE if EXISTS Kalendarz;

CREATE TABLE Kalendarz (
    ID_Wydarzenia INT PRIMARY KEY AUTO_INCREMENT,
    ID_Uzytkownika INT,
    Tytul_wydarzenia VARCHAR(100),
    Data_rozpoczecia DATETIME,
    Data_zakonczenia DATETIME,
    Opis TEXT,
    Przypomnienie BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);
