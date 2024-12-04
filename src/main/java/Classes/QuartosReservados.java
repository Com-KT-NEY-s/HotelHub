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

    // Constructor to initialize QuartosReservados attributes
    public QuartosReservados(String hospede, String quarto, String data_entrada, String data_saida, Double valor) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.valor = valor;
    }

    // Method to insert a reserved room into the database
    public void inserirQuartoReservado(String hospede, String quarto, double valor, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into quartosreservados table
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO quartosreservados (hospede, quarto, valor, data_entrada, data_saida) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, hospede); // Set 'hospede' parameter
            stmt.setString(2, quarto); // Set 'quarto' parameter
            stmt.setDouble(3, valor); // Set 'valor' parameter
            stmt.setString(4, data_entrada); // Set 'data_entrada' parameter
            stmt.setString(5, data_saida); // Set 'data_saida' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
