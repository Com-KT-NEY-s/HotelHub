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

    // Constructor to initialize Reservas attributes
    public Reservas(String hospede, String quarto, String servico, String data_entrada, String data_saida, Double total) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.servico = servico;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.total = total;
    }

    // Method to insert a reservation into the database
    public void inserirReserva(String hospede, String quarto, String servico, String data_entrada, String data_saida, Double total) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into reservas table
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO reservas (hospede, quarto, servico, data_entrada, data_saida, total) VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, hospede); // Set 'hospede' parameter
            stmt.setString(2, quarto); // Set 'quarto' parameter
            stmt.setString(3, servico); // Set 'servico' parameter
            stmt.setString(4, data_entrada); // Set 'data_entrada' parameter
            stmt.setString(5, data_saida); // Set 'data_saida' parameter
            stmt.setDouble(6, total); // Set 'total' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
