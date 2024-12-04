package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Quartos {

    private String tipo, numero, preco, disponivel;

    // Getter and Setter for 'tipo'
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter and Setter for 'numero'
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Getter and Setter for 'preco'
    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    // Getter and Setter for 'disponivel'
    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    // Method to insert a new room into the database
    public void inserirQuarto(String tipo, String numero, double preco, String disponivel) {
        Connection conn = Database.getConnection();
        try {
            // SQL query to insert data into quartos table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quartos (tipo, numero, preco, disponivel) VALUES (?, ?, ?, ?)");
            stmt.setString(1, tipo); // Set 'tipo' parameter
            stmt.setString(2, numero); // Set 'numero' parameter
            stmt.setDouble(3, preco); // Set 'preco' parameter
            stmt.setString(4, disponivel); // Set 'disponivel' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to update the availability of a room
    public void atualizarDisponibilidade(String numero, String disponivel) {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        try {
            // SQL query to update 'disponivel' column
            stmt = conn.prepareStatement("UPDATE quartos SET disponivel = ? WHERE numero = ?");
            stmt.setString(1, disponivel); // New value for 'disponivel'
            stmt.setString(2, numero); // Room identifier (numero)

            int rowsUpdated = stmt.executeUpdate(); // Execute the update query

            // Check if any rows were updated
            if (rowsUpdated > 0) {
                // Update successful
            } else {
                // No rows were updated
            }
        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
