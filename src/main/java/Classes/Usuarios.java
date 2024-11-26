package Classes;

import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuarios {

    private String nome, idade, cpf, senha;

    public Usuarios(String nome, String idade, String cpf, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.senha = senha;
    }

    public void inserirUser(String nome, int idade, String cpf, String senha) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (nome, idade, cpf, senha) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setString(3, cpf);
            stmt.setString(4, senha);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
