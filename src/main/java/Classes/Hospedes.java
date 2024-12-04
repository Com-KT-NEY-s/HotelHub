package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hospedes {

    private String nome, email, idade, cpf, quemCadastrou;

    // Constructor to initialize Hospedes attributes
    public Hospedes(String nome, String email, String cpf, String idade, String quemCadastrou) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
    }

    // Method to insert a new guest into the database
    public void inserirHospede(String nome, String email, String cpf, int idade, String quemCadastrou) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into hospedes table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO hospedes (nome, email, cpf, idade, quemCadastrou) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome); // Set 'nome' parameter
            stmt.setString(2, email); // Set 'email' parameter
            stmt.setString(3, cpf); // Set 'cpf' parameter
            stmt.setInt(4, idade); // Set 'idade' parameter
            stmt.setString(5, quemCadastrou); // Set 'quemCadastrou' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Hospedes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
