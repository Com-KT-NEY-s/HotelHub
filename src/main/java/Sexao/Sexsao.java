package Sexao;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Sexsao {

    private static String usuarioLogado; // Armazena o nome do usuário logado.

    // Getter para recuperar o nome do usuário logado.
    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    // Setter para atualizar o nome do usuário logado.
    public static void setUsuarioLogado(String usuarioLogado) {
        Sexsao.usuarioLogado = usuarioLogado;
    }

    // Método para buscar o nome do usuário com base no CPF (usando o nome de usuário como CPF).
    public static String getNomePorCpf() {
        String nome = null;

        // Conecta ao banco de dados usando a classe Database.
        try (Connection conn = Database.getConnection()) {
            // Consulta SQL para buscar o nome do usuário baseado no CPF
            String query = "SELECT nome FROM usuarios WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, usuarioLogado); // Usa o CPF armazenado no usuário logado.

                // Executa a consulta
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        nome = rs.getString("nome"); // Recupera o nome do usuário.
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe os erros caso ocorra alguma exceção.
        }

        return nome; // Retorna o nome encontrado ou null se não encontrado.
    }
}
