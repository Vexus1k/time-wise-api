DROP TABLE if EXISTS Udostepnienia;

CREATE TABLE Udostepnienia (
    ID_Udostepnienia INT AUTO_INCREMENT PRIMARY KEY,
    ID_Uzytkownika INT,
    ID_Wpisu_Udostepnionego INT,
    Data_udostepnienia DATETIME,
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika),
    FOREIGN KEY (ID_Wpisu_Udostepnionego) REFERENCES Spolecznosc(ID_Wpisu)
);