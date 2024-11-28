CREATE TABLE hospedes (
         id_hospedes INTEGER PRIMARY KEY AUTOINCREMENT,
         id_quartos,
         nome TEXT,
         idade TEXT,
         cpf TEXT,
         FOREIGN KEY (id_quartos) REFERENCES quartos (id_quartos)
);
