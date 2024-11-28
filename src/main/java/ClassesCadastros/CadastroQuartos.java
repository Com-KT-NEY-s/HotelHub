package ClassesCadastros;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroQuartos {

    private String tipo, numero, preco, disponivel;

    public CadastroQuartos(String tipo, String numero, String preco, String disponivel) {
        this.tipo = tipo;
        this.numero = numero;
        this.preco = preco;
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
            Logger.getLogger(CadastroQuartos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
