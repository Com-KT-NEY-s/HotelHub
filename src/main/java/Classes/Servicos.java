package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servicos {

    private String tipo, preco;

    // Constructor to initialize Servicos attributes
    public Servicos(String tipo, String preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    // Method to insert a new service into the database
    public void inserirServicos(String tipo, double preco) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into servicos table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO servicos (tipo, preco) VALUES (?, ?)");
            stmt.setString(1, tipo); // Set 'tipo' parameter
            stmt.setDouble(2, preco); // Set 'preco' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
