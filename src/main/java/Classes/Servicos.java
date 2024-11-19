package Classes;

import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Servicos {
    String tipo;
    double preco;

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
    public void criar() {
        String sql = "INSERT INTO servicos (tipo,  preco) VALUES (?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.tipo);
            pstmt.setDouble(2, this.preco);
            
            pstmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
        public String[][] ler() {
        String sql = "SELECT * FROM servicos";
        List<String[]> linhas = new ArrayList<>();
        
        try(Connection conn = Database.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {
            int colunas = rs.getMetaData().getColumnCount();
            
            while (rs.next()) {
                String[] linha = new String[colunas];
                for (int i = 1; i <= colunas; i++) {
                    linha[i - 1] = rs.getString(i);
                }
                linhas.add(linha);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] resultado = new String[linhas.size()][];
        resultado = linhas.toArray(resultado);
        
        return resultado;
    }
}