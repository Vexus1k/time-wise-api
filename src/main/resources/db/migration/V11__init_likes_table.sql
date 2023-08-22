DROP TABLE if EXISTS Polubienia;

CREATE TABLE Polubienia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ID_Uzytkownika INT,
    ID_Wpisu INT,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika),
    FOREIGN KEY (ID_Wpisu) REFERENCES Spolecznosc(ID_Wpisu)
);