package com.mycompany.softwarezika;

import DataBase.Database;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePickerImpl;

public class WinReservas extends javax.swing.JFrame {

    double diasReservadosDoub;
    double valorTotal;
    private JPopupMenu popupMenu;
    private JPanel sugestoesPanel;

    public WinReservas() throws java.text.ParseException {
        initComponents();
        reservarBtn.addActionListener(e -> exibirDiasReservados());
        configurarFormatadores();

        hospedeJtx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarSugestoes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarSugestoes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarSugestoes();
            }

            private void atualizarSugestoes() {
                String textoDigitado = hospedeJtx.getText().trim();
                if (!textoDigitado.isEmpty()) {
                    listarHospedes(textoDigitado);
                } else {
                    sugestoesPanel.setVisible(false);
                }
            }
        });

        sugestoesPanel = new JPanel();
        sugestoesPanel.setLayout(new BoxLayout(sugestoesPanel, BoxLayout.Y_AXIS));
        sugestoesPanel.setVisible(false);
        sugestoesPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        sugestoesPanel.setBackground(Color.WHITE);

        // Adicione o painel próximo ao JTextField
        add(sugestoesPanel);

        listarQuartos();
        listarServicos();
        
        JDatePicker calendario = new JDatePickerImpl(datePanel, formatter);
    }
    
    
    private void configurarFormatadores() throws java.text.ParseException {
        // Criando o MaskFormatter para o formato de data (dd/MM/yyyy)
        MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
        dateFormatter.setPlaceholderCharacter('_');

        // Configurando os JFormattedTextFields com o MaskFormatter
        dataEntradaJtx = new JFormattedTextField(dateFormatter);
        dataSaidaJtx = new JFormattedTextField(dateFormatter);
    }

    private void exibirDiasReservados() {
        try {
            // Obter as entradas de data
            String entradaData = dataEntradaJtx.getText().replace("_", "").trim();
            String saidaData = dataSaidaJtx.getText().replace("_", "").trim();

            // Verificar se ambos os campos foram preenchidos
            if (entradaData.length() != 10 || saidaData.length() != 10) {
                JOptionPane.showMessageDialog(this, "Por favor, insira ambas as datas no formato dd/MM/yyyy.");
                return;
            }

            // Define o formato das datas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Converte as datas para LocalDate
            LocalDate dataEntrada = LocalDate.parse(entradaData, formatter);
            LocalDate dataSaida = LocalDate.parse(saidaData, formatter);

            // Verifica se a data de entrada é posterior à data de saída
            if (dataEntrada.isAfter(dataSaida)) {
                JOptionPane.showMessageDialog(this, "A data de entrada não pode ser posterior à data de saída.");
                return;
            }

            // Calcula a diferença em dias
            long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            diasReservadosDoub = (double) dias;

            // Exibe os dias reservados
            JOptionPane.showMessageDialog(this, "Dias reservados: " + diasReservadosDoub);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida! Por favor, insira as datas no formato dd/MM/yyyy.");
        }
    }

    private void listarHospedes(String textoDigitado) {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Consulta os hóspedes com base no texto digitado (por nome ou CPF)
            String sql = "SELECT `id_usuario`, `nome`, `cpf` FROM `usuarios` WHERE `nome` LIKE ? OR `cpf` LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + textoDigitado + "%");
            stmt.setString(2, "%" + textoDigitado + "%");
            rs = stmt.executeQuery();

            // Limpa as sugestões atuais
            sugestoesPanel.removeAll();
            sugestoesPanel.setVisible(false);

            // Adiciona novos resultados ao painel de sugestões
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                JLabel sugestao = new JLabel(idUsuario + " - " + nome + " (" + cpf + ")");
                sugestao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                sugestao.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                // Clique na sugestão para preencher o campo
                sugestao.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        hospedeJtx.setText(nome);
                        sugestoesPanel.setVisible(false);
                    }
                });

                sugestoesPanel.add(sugestao);
            }

            // Exibe o painel de sugestões se houver resultados
            if (sugestoesPanel.getComponentCount() > 0) {
                sugestoesPanel.setVisible(true);
                sugestoesPanel.revalidate();
                sugestoesPanel.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fazerReserva() {
        // Pegando os valores selecionados nos ComboBoxes
        String tipoQuartoSelecionado = (String) comboQ.getSelectedItem();
        String tipoServicoSelecionado = (String) comboS.getSelectedItem();
        String textoHospede = hospedeJtx.getText().trim();

        // Validando os valores selecionados
        if (tipoQuartoSelecionado == null || tipoServicoSelecionado == null || textoHospede.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um quarto, serviço e insira um hóspede válido.");
            return;
        }

        // Variáveis para armazenar os preços e IDs
        double precoQuarto = 0;
        double precoServico = 0;
        int idHospede = 0;
        int idQuarto = 0;

        try (Connection conn = Database.getConnection()) {
            // Buscar ID do hóspede baseado no texto digitado (nome ou CPF)
            String sqlHospede = "SELECT `id_usuario` FROM `usuarios` WHERE `nome` = ? OR `cpf` = ?";
            try (PreparedStatement stmtHospede = conn.prepareStatement(sqlHospede)) {
                stmtHospede.setString(1, textoHospede); // Procurar pelo nome
                stmtHospede.setString(2, textoHospede); // Procurar pelo CPF
                try (ResultSet rsHospede = stmtHospede.executeQuery()) {
                    if (rsHospede.next()) {
                        idHospede = rsHospede.getInt("id_usuario");
                    } else {
                        JOptionPane.showMessageDialog(this, "Hóspede não encontrado! Verifique o nome ou CPF digitado.");
                        return;
                    }
                }
            }

            // Buscar preço e ID do quarto
            String sqlQuarto = "SELECT `id_quarto`, `preco` FROM `quartos` WHERE `tipo` = ?";
            try (PreparedStatement stmtQuarto = conn.prepareStatement(sqlQuarto)) {
                stmtQuarto.setString(1, tipoQuartoSelecionado);
                try (ResultSet rsQuarto = stmtQuarto.executeQuery()) {
                    if (rsQuarto.next()) {
                        idQuarto = rsQuarto.getInt("id_quarto");
                        precoQuarto = rsQuarto.getDouble("preco");
                    } else {
                        JOptionPane.showMessageDialog(this, "Quarto não encontrado!");
                        return;
                    }
                }
            }

            // Buscar preço do serviço
            String sqlServico = "SELECT `preco` FROM `servicos` WHERE `tipo` = ?";
            try (PreparedStatement stmtServico = conn.prepareStatement(sqlServico)) {
                stmtServico.setString(1, tipoServicoSelecionado);
                try (ResultSet rsServico = stmtServico.executeQuery()) {
                    if (rsServico.next()) {
                        precoServico = rsServico.getDouble("preco");
                    } else {
                        JOptionPane.showMessageDialog(this, "Serviço não encontrado!");
                        return;
                    }
                }
            }

            // Calcular o valor total
            valorTotal = (precoQuarto * diasReservadosDoub) + precoServico;

            // Inserir a reserva no banco de dados
            String sqlInserir = "INSERT INTO `reservas` (`data_entrada`, `data_saida`, `id_hospede`, `id_quarto`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtInserir = conn.prepareStatement(sqlInserir)) {
                stmtInserir.setString(1, dataEntradaJtx.getText()); // Data de entrada como String
                stmtInserir.setString(2, dataSaidaJtx.getText());   // Data de saída como String
                stmtInserir.setInt(3, idHospede);
                stmtInserir.setInt(4, idQuarto);

                int linhasAfetadas = stmtInserir.executeUpdate();
                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso! Valor total: R$ " + valorTotal);
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao realizar a reserva.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.");
        }
    }

    private void listarQuartos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT `tipo` FROM `quartos`";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboQ.getModel();

            model.removeAllElements();

            while (rs.next()) {
                String quarto = rs.getString("tipo");
                model.addElement(quarto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listarServicos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT `tipo` FROM `servicos`";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboS.getModel();

            model.removeAllElements();

            while (rs.next()) {
                String servico = rs.getString("tipo");
                model.addElement(servico);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hospedeJtx = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        reservarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbxReservas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboQ = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboS = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dataEntradaJtx = new javax.swing.JTextField();
        dataSaidaJtx = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Procurar hospede:");

        hospedeJtx.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Escolher Quarto:");

        reservarBtn.setBackground(new java.awt.Color(247, 151, 29));
        reservarBtn.setForeground(new java.awt.Color(0, 0, 0));
        reservarBtn.setText("Reservar");
        reservarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarBtnActionPerformed(evt);
            }
        });

        tbxReservas.setForeground(new java.awt.Color(0, 0, 0));
        tbxReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Hóspede", "Quarto", "Servicos", "DataEntrada", "DataSaida", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tbxReservas);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RESERVAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28))))
        );

        comboQ.setForeground(new java.awt.Color(0, 0, 0));
        comboQ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Serviços:");

        comboS.setForeground(new java.awt.Color(0, 0, 0));
        comboS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(153, 51, 255));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Data de Entrada:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Data de Saída:");

        dataEntradaJtx.setForeground(new java.awt.Color(0, 0, 0));

        dataSaidaJtx.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataEntradaJtx))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dataSaidaJtx, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataEntradaJtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataSaidaJtx))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hospedeJtx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboS, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(hospedeJtx, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(comboQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reservarBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void reservarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservarBtnActionPerformed
        fazerReserva();
    }//GEN-LAST:event_reservarBtnActionPerformed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WinReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WinReservas().setVisible(true);
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboQ;
    private javax.swing.JComboBox<String> comboS;
    private javax.swing.JTextField dataEntradaJtx;
    private javax.swing.JTextField dataSaidaJtx;
    private javax.swing.JTextField hospedeJtx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reservarBtn;
    private javax.swing.JTable tbxReservas;
    // End of variables declaration//GEN-END:variables
}
