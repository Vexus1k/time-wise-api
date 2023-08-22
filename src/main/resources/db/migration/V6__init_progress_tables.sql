DROP TABLE if EXISTS Postepy;

CREATE TABLE Postepy (
    ID_Postepu INT PRIMARY KEY AUTO_INCREMENT,
    ID_Uzytkownika INT,
    ID_Celu INT,
    Data_wpisu DATETIME,
    Opis_postepow VARCHAR(255),
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika),
    FOREIGN KEY (ID_Celu) REFERENCES Cele(ID_Celu)
);