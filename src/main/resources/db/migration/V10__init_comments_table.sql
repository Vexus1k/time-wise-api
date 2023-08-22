DROP TABLE if EXISTS Komentarze;

CREATE TABLE Komentarze (
    ID_Komentarza INT PRIMARY KEY AUTO_INCREMENT,
    ID_Wpisu INT,
    ID_Uzytkownika INT,
    Tresc VARCHAR(255),
    Data_dodania DATETIME,
    FOREIGN KEY (ID_Wpisu) REFERENCES Spolecznosc(ID_Wpisu),
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);