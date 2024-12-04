package ClassesCadastros;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroReservas {

    private String hospede, quarto, data_entrada, data_saida;

    // Constructor to initialize CadastroReservas attributes
    public CadastroReservas(String hospede, String quarto, String data_entrada, String data_saida) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
    }

    // Method to insert a reservation into the database
    public void inserirReserva(String hospede, String quarto, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into reservas table
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO reservas (hospede, quarto, data_entrada, data_saida) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, hospede); // Set 'hospede' parameter
            stmt.setString(2, quarto); // Set 'quarto' parameter
            stmt.setString(3, data_entrada); // Set 'data_entrada' parameter
            stmt.setString(4, data_saida); // Set 'data_saida' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(CadastroReservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroReservas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
