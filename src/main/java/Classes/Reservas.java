package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reservas {

    private String hospede, quarto, servico, data_entrada, data_saida;
    private Double total;

    public Reservas(String hospede, String quarto, String servico, String data_entrada, String data_saida, Double total) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.servico = servico;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.total = total;
    }

    public void inserirReserva(String hospede, String quarto, String servico, String data_entrada, String data_saida, Double total) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservas (hospede, quarto, servico, data_entrada, data_saida, total) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, hospede);
            stmt.setString(2, quarto);
            stmt.setString(3, servico);
            stmt.setString(4, data_entrada);
            stmt.setString(5, data_saida);
            stmt.setDouble(6, total);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
