DROP TABLE if EXISTS Sugestie;

CREATE TABLE Sugestie (
    ID_Sugestii INT PRIMARY KEY AUTO_INCREMENT,
    Tresc_sugestii TEXT,
    Powiazane_tagi VARCHAR(100),
    Kategoria_sugestii VARCHAR(20)
);
