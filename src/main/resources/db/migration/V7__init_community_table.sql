DROP TABLE if EXISTS Spolecznosc;

CREATE TABLE Spolecznosc (
    ID_Wpisu INT PRIMARY KEY AUTO_INCREMENT,
    ID_Uzytkownika INT,
    Tresc_wpisu VARCHAR(255),
    Data_publikacji DATETIME,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);
