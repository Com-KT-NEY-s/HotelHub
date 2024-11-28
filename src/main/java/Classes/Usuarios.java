package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class representing users with attributes and methods to manage user data in the database.
public class Usuarios {

    private String nome, cpf, senha; // User attributes: name, CPF, and password.
    private int idade; // User's age

    // Constructor to initialize a user with all attributes.
    public Usuarios(String nome, int idade, String cpf, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Method to insert a new user into the database.
    public boolean inserirUser(String nome, int idade, String cpf, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isAdm = false;

        try {
            // Establishing the connection to the database
            conn = Database.getConnection();

            // Check if it's the first user to determine admin status
            String checkSql = "SELECT COUNT(*) FROM usuarios";
            stmt = conn.prepareStatement(checkSql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userCount = rs.getInt(1);
                // If this is the first user, assign admin status
                if (userCount == 0) {
                    isAdm = true;
                }
            }

            // Insert the new user into the database
            String sql = "INSERT INTO usuarios (nome, idade, cpf, senha, is_adm) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Set parameters for the SQL query
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, cpf);
            stmt.setString(4, senha);
            stmt.setBoolean(5, isAdm); // Set the admin status

            // Execute the query and check if the insertion was successful
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted

        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Return false if there was an error during insertion
        } finally {
            // Close resources in the 'finally' block to ensure they are always closed
            try {
                if (stmt != null) {
                    stmt.close(); // Close the PreparedStatement
                }
                if (conn != null) {
                    conn.close(); // Close the database connection
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log any errors that occur while closing resources
            }
        }
    }
}


