package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hospedes {

    private String nome, email, idade, cpf, quemCadastrou;

    public Hospedes(String nome, String email, String cpf, String idade, String quemCadastrou) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
    }

    public void inserirHospede(String nome, String email, String cpf, int idade, String quemCadastrou) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO hospedes (nome, email, cpf, idade, quemCadastrou) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);
            stmt.setInt(4, idade);
            stmt.setString(5, quemCadastrou);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Hospedes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
