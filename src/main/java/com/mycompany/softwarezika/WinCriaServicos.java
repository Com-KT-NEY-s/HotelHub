package com.mycompany.softwarezika;

import Classes.Servicos;
import DataBase.Database;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class WinCriaServicos extends javax.swing.JFrame {

    private DefaultTableModel tabelaServicos = new DefaultTableModel(new Object[]{"Serviço", "Valor"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // LISTAR NA TABELA
    // CRUD DA TABELA
    public WinCriaServicos() {
        initComponents();

        JTservicos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    excluirLinhaSelecionada();
                }
            }
        });

        listarServicos();
    }

    private void excluirLinhaSelecionada() {
        // Obtém o índice da linha selecionada na tabela
        int linhaSelecionada = JTservicos.getSelectedRow();

        if (linhaSelecionada != -1) { // Verifica se há uma linha selecionada
            // Obtém o id_servico da linha selecionada (presumindo que o id_servico está na coluna 0 da tabela)
            int idServico = (int) JTservicos.getValueAt(linhaSelecionada, 0);

            // Pergunta se o usuário tem certeza de que deseja excluir
            int resposta = javax.swing.JOptionPane.showConfirmDialog(
                    this, "Tem certeza que deseja excluir este serviço?",
                    "Excluir Serviço", javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (resposta == javax.swing.JOptionPane.YES_OPTION) {
                // Exclui o serviço do banco de dados
                excluirServicoDoBanco(idServico);

                // Remove a linha da tabela
                DefaultTableModel modeloTabela = (DefaultTableModel) JTservicos.getModel();
                modeloTabela.removeRow(linhaSelecionada);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirServicoDoBanco(int idServico) {
        String deleteSql = "DELETE FROM servicos WHERE id_servico = ?";

        try (Connection conn = Database.getConnection(); PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            // Define o id_servico no PreparedStatement
            deleteStmt.setInt(1, idServico);

            int rowsAffected = deleteStmt.executeUpdate(); // Executa a exclusão no banco de dados

            if (rowsAffected > 0) {
                System.out.println("Serviço excluído com sucesso.");
            } else {
                System.out.println("Erro ao excluir serviço.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criar(String tipo, double preco) {
        String insertSql = "INSERT INTO servicos (tipo, preco) VALUES (?, ?)";
        String selectSql = "SELECT id_servico, tipo, preco FROM servicos";

        try (Connection conn = Database.getConnection(); PreparedStatement insertStmt = conn.prepareStatement(insertSql); PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            // Insere os dados na tabela "servicos"
            insertStmt.setString(1, tipo); // Tipo do serviço
            insertStmt.setDouble(2, preco); // Preço do serviço
            int rowsAffected = insertStmt.executeUpdate(); // Usar executeUpdate() para garantir a inserção

            if (rowsAffected > 0) {
                System.out.println("Inserção bem-sucedida.");
            } else {
                System.out.println("Nenhuma linha inserida.");
            }

            // Chama o método listarServicos() para atualizar a tabela
            listarServicos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listarServicos() {
        String selectSql = "SELECT id_servico, tipo, preco FROM servicos";

        try (Connection conn = Database.getConnection(); PreparedStatement selectStmt = conn.prepareStatement(selectSql); ResultSet rs = selectStmt.executeQuery()) {

            tabelaServicos.setRowCount(0); // Limpa a tabela

            // Adiciona as linhas à tabela
            while (rs.next()) {
                Object[] row = {
                    rs.getString("tipo"), // Tipo do serviço
                    rs.getDouble("preco") // Preço do serviço
                };
                tabelaServicos.addRow(row); // Adiciona a linha na tabela
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTservicos = new javax.swing.JTable();
        txtValorS = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAdicionarServicos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTipoS = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(600, 500));

        jLabel5.setText("Valor:");

        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        JTservicos.setModel(tabelaServicos);
        JTservicos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTservicos.setDoubleBuffered(true);
        jScrollPane1.setViewportView(JTservicos);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADICIONAR SERVIÇOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAdicionarServicos.setBackground(new java.awt.Color(255, 153, 0));
        btnAdicionarServicos.setForeground(new java.awt.Color(0, 0, 0));
        btnAdicionarServicos.setText("Adicionar Serviços");
        btnAdicionarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarServicosActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(txtTipoS)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtValorS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(btnAdicionarServicos)
                        .addGap(0, 158, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarServicosActionPerformed
        String tipo = txtTipoS.getText();
        double preco;

        try {
            preco = Double.parseDouble(txtValorS.getText());
        } catch (NumberFormatException e) {
            // Exiba uma mensagem de erro se o valor não for válido
            javax.swing.JOptionPane.showMessageDialog(this, "Valor inválido! Digite um número.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        criar(tipo, preco);
    }//GEN-LAST:event_btnAdicionarServicosActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WinCriaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinCriaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinCriaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinCriaServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinCriaServicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTservicos;
    private javax.swing.JButton btnAdicionarServicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtTipoS;
    private javax.swing.JTextField txtValorS;
    // End of variables declaration//GEN-END:variables
}
