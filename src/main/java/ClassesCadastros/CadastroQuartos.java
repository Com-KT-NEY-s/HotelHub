package ClassesCadastros;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroQuartos {

    private String tipo, numero, preco, disponivel;

    // Constructor to initialize CadastroQuartos attributes
    public CadastroQuartos(String tipo, String numero, String preco, String disponivel) {
        this.tipo = tipo;
        this.numero = numero;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    // Method to insert a room into the database
    public void inserirQuarto(String tipo, String numero, double preco, String disponivel) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into quartos table
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO quartos (tipo, numero, preco, disponivel) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, tipo); // Set 'tipo' parameter
            stmt.setString(2, numero); // Set 'numero' parameter
            stmt.setDouble(3, preco); // Set 'preco' parameter
            stmt.setString(4, disponivel); // Set 'disponivel' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(CadastroQuartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroQuartos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
