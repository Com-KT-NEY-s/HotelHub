package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servicos {

    private String tipo, preco;

    public Servicos(String tipo, String preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public void inserirServicos(String tipo, double preco) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO servicos (tipo, preco) VALUES (?, ?)");
            stmt.setString(1, tipo);
            stmt.setDouble(2, preco);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
