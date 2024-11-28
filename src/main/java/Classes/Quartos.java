package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Quartos {

    private String tipo, numero, preco, disponivel;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public void inserirQuarto(String tipo, String numero, double preco, String disponivel) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quartos (tipo, numero, preco, disponivel) VALUES (?, ?, ?, ?)");
            stmt.setString(1, tipo);
            stmt.setString(2, numero);
            stmt.setDouble(3, preco);
            stmt.setString(4, disponivel);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarDisponibilidade(String numero, String disponivel) {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        try {
            // Comando SQL para atualizar apenas o campo "disponivel"
            stmt = conn.prepareStatement("UPDATE quartos SET disponivel = ? WHERE numero = ?");
            stmt.setString(1, disponivel); // Novo valor para "disponivel"
            stmt.setString(2, numero);    // Critério para identificar o quarto (número)

            // Executa o comando de atualização
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar os recursos
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
