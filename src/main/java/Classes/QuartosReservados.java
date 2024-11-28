
package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuartosReservados {
    private String hospede, quarto, data_entrada, data_saida;
    private Double valor;

    public QuartosReservados(String hospede, String quarto, String data_entrada, String data_saida, Double valor) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.valor = valor;
    }
    public void inserirQuartoReservado(String hospede, String quarto, double valor, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quartosreservados (hospede, quarto, valor, data_entrada, data_saida) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, hospede);
            stmt.setString(2, quarto);
            stmt.setDouble(3, valor);
            stmt.setString(4, data_entrada);
            stmt.setString(5, data_saida);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
