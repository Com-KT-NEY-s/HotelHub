package Classes;

import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Class representing services with attributes and methods to interact with the database.
public class Servicos {

    String tipo; // Type of service
    double preco; // Price of the service

    // Getters and setters for the attributes
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Method to create a new service record in the database
    public void criar() {
        String sql = "INSERT INTO servicos (tipo, preco) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.tipo);
            pstmt.setDouble(2, this.preco);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to read all service records from the database
    public String[][] ler() {
        String sql = "SELECT * FROM servicos";
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

        // Convert list of service records to a 2D array
        String[][] resultado = new String[linhas.size()][];
        resultado = linhas.toArray(resultado);

        return resultado;
    }
}
