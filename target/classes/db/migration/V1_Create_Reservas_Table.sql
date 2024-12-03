CREATE TABLE reservas (
    id_reservas INTEGER PRIMARY KEY AUTOINCREMENT,
    id_hospedes INTEGER,
    horario_reserva DATETIME,
    data_entrada DATETIME,
    data_saida DATETIME,
    FOREIGN KEY (id_hospedes) REFERENCES hospedes (id_hospedes)
);

