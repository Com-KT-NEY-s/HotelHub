package cadastros;

import DataBase.Database;
import Sexao.Sexsao;
import home.HotelHubLogado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class WinCriaQuartos extends javax.swing.JFrame {

    private DefaultTableModel tabelaQuartos = new DefaultTableModel(new Object[]{"Tipo", "Número", "Preço", "Disponibilidade"}, 0);

    public WinCriaQuartos() {
        initComponents();
        setTitle("Cadastro de Quartos!");
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JFrame j = new HotelHubLogado();
                j.setVisible(true);
                j.setLocationRelativeTo(null);
            }
        });

        // Adiciona o KeyListener à tabela
        tblCriaQuartos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                    deleteSelectedRow(); // Método para excluir a linha selecionada
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCriaQuartos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAdicionarQuartos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(700, 400));

        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        tblCriaQuartos.setModel(tabelaQuartos);
        tblCriaQuartos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCriaQuartos.setDoubleBuffered(true);
        jScrollPane1.setViewportView(tblCriaQuartos);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADICIONAR QUARTO");

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

        btnAdicionarQuartos.setBackground(new java.awt.Color(255, 153, 0));
        btnAdicionarQuartos.setForeground(new java.awt.Color(0, 0, 0));
        btnAdicionarQuartos.setText("Adicionar Quartos");
        btnAdicionarQuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarQuartosActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        jLabel3.setText("Numero:");

        jLabel5.setText("Valor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarQuartos, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(txtValor)
                        .addComponent(txtTipo)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnAdicionarQuartos)
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSelectedRow() {
        int selectedRow = tblCriaQuartos.getSelectedRow(); // Obtém a linha selecionada

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Nenhuma linha selecionada para exclusão.");
            return;
        }

        // Obtém os valores da linha selecionada (baseado nas colunas da tabela)
        String numero = tblCriaQuartos.getValueAt(selectedRow, 1).toString(); // Número do quarto (usado como identificador)

        // Confirmação de exclusão
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza de que deseja excluir o quarto selecionado?",
                "Confirmação de Exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                // Conecta ao banco de dados
                conn = Database.getConnection();

                // Exclui o registro no banco de dados
                String sql = "DELETE FROM quartos WHERE numero = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, numero);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Remove a linha da tabela gráfica
                    DefaultTableModel model = (DefaultTableModel) tblCriaQuartos.getModel();
                    model.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(this, "Quarto excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Quarto não encontrado no banco de dados.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(WinCriaQuartos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao excluir quarto: " + ex.getMessage());
            } finally {
                // Fecha a conexão com o banco
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(WinCriaQuartos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    private void btnAdicionarQuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarQuartosActionPerformed
        String tipo = txtTipo.getText();
        String numero = txtNumero.getText();
        String valorStr = txtValor.getText();

        double valorFinalQ = Double.parseDouble(valorStr);

        boolean disponivel = true;
        String disponivelStr = "";

        // Validações simples para garantir que os campos não estão vazios
        if (tipo.isEmpty() || numero.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        if (disponivel) {
            disponivelStr = "Disponível";
        } else {
            disponivelStr = "N/ Disponível";
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Estabelece a conexão com o banco de dados
            conn = Database.getConnection();

            // Inserção na tabela 'quartos' do banco de dados
            String sql = "INSERT INTO quartos (tipo, numero, preco, disponivel) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipo); // Tipo do quarto
            stmt.setString(2, numero); // Número do quarto
            stmt.setDouble(3, valorFinalQ); // Preço do quarto
            stmt.setString(4, disponivelStr); // Disponível (assumindo que é booleano)

            int rowsAffected = stmt.executeUpdate(); // Executa a inserção no banco de dados

            if (rowsAffected > 0) {
                // Atualiza a tabela da interface gráfica (tblCriaQuartos)
                DefaultTableModel model = (DefaultTableModel) tblCriaQuartos.getModel();

                model.addRow(new Object[]{tipo, numero, valorStr, disponivelStr});

                JOptionPane.showMessageDialog(null, "Quarto adicionado com sucesso!");

                // Limpa os campos após a inserção
                txtTipo.setText("");
                txtNumero.setText("");
                txtValor.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar o quarto.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(WinCriaQuartos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao adicionar quarto: " + ex.getMessage());
        } finally {
            // Fechando a conexão e o PreparedStatement para liberar os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(WinCriaQuartos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnAdicionarQuartosActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //    new WinCriaQuartos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarQuartos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCriaQuartos;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

}
