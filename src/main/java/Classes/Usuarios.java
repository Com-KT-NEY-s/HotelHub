package Classes;

import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Class representing users with attributes and methods to manage user data in the database.
public class Usuarios {

    private String nome, cpf, senha; // User attributes: name, age, CPF (ID), and password.
    private int idade;

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

        try {
            conn = Database.getConnection(); // Estabelece conexão com o banco de dados.
            String sql = "INSERT INTO usuarios (nome, idade, cpf, senha) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Define os parâmetros para o SQL.
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, cpf);
            stmt.setString(4, senha);

            // Executa o comando e verifica se a inserção ocorreu.
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retorna true se ao menos uma linha foi inserida.

        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Retorna false em caso de erro.
        } finally {
            try {
                if (stmt != null) {
                    stmt.close(); // Fecha o PreparedStatement.
                }
                if (conn != null) {
                    conn.close(); // Fecha a conexão.
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
