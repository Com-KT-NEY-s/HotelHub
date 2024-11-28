package ClassesCadastros;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroReservas {

    private String hospede, quarto, data_entrada, data_saida;

    public CadastroReservas(String hospede, String quarto, String data_entrada, String data_saida) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
    }

    public void inserirReserva(String hospede, String quarto, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservas (hospede, quarto, data_entrada, data_saida) VALUES (?, ?, ?, ?)");
            stmt.setString(1, hospede);
            stmt.setString(2, quarto);
            stmt.setString(3, data_entrada);
            stmt.setString(4, data_saida);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(CadastroReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
