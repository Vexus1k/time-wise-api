DROP TABLE if EXISTS Uzytkownicy;

CREATE TABLE Uzytkownicy (
    ID_Uzytkownika INT PRIMARY KEY AUTO_INCREMENT,
    Imie VARCHAR(50),
    Nazwisko VARCHAR(50),
    Adres_email VARCHAR(100),
    Numer_telefonu VARCHAR(20),
    Haslo VARCHAR(128),
    Data_rejestracji DATETIME,
    Aktywowany BOOLEAN DEFAULT FALSE,
    Token_aktywacyjny VARCHAR(255),
    Rola VARCHAR(50)
);

