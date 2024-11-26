package Classes;

import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Class representing rooms with attributes, database interaction methods, and utilities.
public class Quartos {

    String tipo, numero; // Room type and number
    double preco; // Room price
    boolean disponivel; // Room availability

    // Getters and setters for the attributes
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Method to create a new room record in the database
    public void criar() {
        String sql = "INSERT INTO quartos (numero, tipo, disponivel, preco) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.numero);
            pstmt.setString(2, this.tipo);
            pstmt.setBoolean(3, this.disponivel);
            pstmt.setDouble(4, this.preco);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to read all room records from the database
    public String[][] ler() {
        String sql = "SELECT * FROM quartos";
        List<String[]> linhas = new ArrayList<>();

        try (Connection conn = Database.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {
            int colunas = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String[] linha = new String[colunas];
                for (int i = 1; i <= colunas; i++) {
                    linha[i - 1] = rs.getString(i);
                }
                linhas.add(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert list of room records to array
        String[][] resultado = new String[linhas.size()][];
        resultado = linhas.toArray(resultado);

        return resultado;
    }
}
