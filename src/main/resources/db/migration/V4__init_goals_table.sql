DROP TABLE if EXISTS Cele;

CREATE TABLE Cele (
    ID_Celu INT PRIMARY KEY AUTO_INCREMENT,
    ID_Uzytkownika INT,
    Tresc_celu TEXT,
    Data_utworzenia DATETIME,
    Termin_wykonania DATETIME,
    Status_celu VARCHAR(50),
    Powiazane_tagi VARCHAR(100),
    FOREIGN KEY (ID_Uzytkownika) REFERENCES Uzytkownicy(ID_Uzytkownika)
);
