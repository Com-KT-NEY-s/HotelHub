package cadastros;

import Classes.Hospedes;
import Classes.Quartos;
import Classes.QuartosReservados;
import Classes.Reservas;
import Database.Database;
import Sexao.Sexsao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class WinReservas extends javax.swing.JFrame {

    private DefaultTableModel tabelaReservas = new DefaultTableModel(new Object[]{"ID", "Hóspede", "Quarto", "Serviços", "Entrada", "Saída", "Total"}, 0);

    public WinReservas() {
        initComponents();
        setTitle("Reserva de Quartos!");  // Set the window title
        listaQuartos();  // List available rooms
        listaReservas();  // List current reservations
        listaServicos();  // List available services
        setLocationRelativeTo(null);  // Center the window on the screen

        // Add functionality to delete selected reservations with the Delete key
        JTreservas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {  // Check if the Delete key was pressed
                    int selectedRow = JTreservas.getSelectedRow();  // Get selected row
                    if (selectedRow != -1) {  // Check if a row is selected
                        // Get the ID of the selected reservation
                        int id = Integer.parseInt(JTreservas.getValueAt(selectedRow, 0).toString());

                        // Call the function to delete the reservation from the database
                        excluirPelaTabelaR(id);

                        // Remove the selected row from the table
                        DefaultTableModel model = (DefaultTableModel) JTreservas.getModel();
                        model.removeRow(selectedRow);

                        // Show a message indicating successful deletion
                        JOptionPane.showMessageDialog(null, "Item excluído com sucesso.");
                    } else {
                        // If no row is selected, show a message
                        JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                    }
                }
            }
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btReservar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataSaida = new javax.swing.JFormattedTextField();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        comboQuartos = new javax.swing.JComboBox<>();
        comboServicos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        edtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTreservas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btReservar.setBackground(new java.awt.Color(247, 151, 29));
        btReservar.setForeground(new java.awt.Color(0, 0, 0));
        btReservar.setText("Reservar");
        btReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReservarActionPerformed(evt);
            }
        });

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(153, 51, 255));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Entrada:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Saída:");

        try {
            txtDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        comboQuartos.setForeground(new java.awt.Color(0, 0, 0));
        comboQuartos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboServicos.setForeground(new java.awt.Color(0, 0, 0));
        comboServicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Quarto:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Serviços:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nome:");

        edtNome.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Idade:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Email:");

        edtIdade.setForeground(new java.awt.Color(0, 0, 0));

        edtEmail.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("CPF:");

        edtCPF.setForeground(new java.awt.Color(0, 0, 0));
        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(edtNome)
                            .addComponent(comboQuartos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboServicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        JTreservas.setModel(tabelaReservas);
        jScrollPane2.setViewportView(JTreservas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(618, 618, 618)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btReservar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Function to delete a reservation from the database
    private static void excluirPelaTabelaR(int id) {
        try (Connection conn = Database.getConnection()) {  // Get connection to the database
            String query = "DELETE FROM quartosreservados WHERE id_quartoReservado = ?";  // SQL query to delete reservation
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);  // Set the ID value in the query

            int rowsAffected = stmt.executeUpdate();  // Execute the query and check how many rows were affected

            if (rowsAffected > 0) {
                System.out.println("Dados excluídos do banco de dados!");  // Print a success message
            } else {
                System.out.println("Nenhum dado foi encontrado com o ID fornecido.");  // If no row is deleted, print a message
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());  // Show error message if something goes wrong
        }
    }


    private void btReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReservarActionPerformed
        String nome = edtNome.getText();  // Get the name from the text field
        String idadeS = edtIdade.getText();  // Get the age from the text field
        String cpf = edtCPF.getText();  // Get the CPF (ID) from the text field
        String email = edtEmail.getText();  // Get the email from the text field
        String quemCadastrou = Sexsao.getUsuarioLogado();  // Get the logged-in user

        String quarto = (String) comboQuartos.getSelectedItem();  // Get selected room
        String servico = (String) comboServicos.getSelectedItem();  // Get selected service
        String dataEntrada = txtDataEntrada.getText();  // Get entry date
        String dataSaida = txtDataSaida.getText();  // Get exit date

        // Split the room data to extract room price and number
        String[] quartoDados = quarto.split(" - ");
        String valorQuarto = quartoDados[1].replace("R$", "").trim();  // Get the price of the room
        String numeroQuarto = quartoDados[2].replace("N°", "").trim();  // Get the room number

        // Split the service data to extract service price
        String[] servicoDados = servico.split(" - ");
        String valorServico = servicoDados[1].replace("R$", "").trim();  // Get the service price

        // Convert the prices to double for calculation
        double valorQuartoD = Double.parseDouble(valorQuarto);
        double valorServicoD = Double.parseDouble(valorServico);

        // Check if all required fields are filled
        if (!nome.isEmpty() && !quarto.isEmpty() && !dataEntrada.isEmpty() && !dataSaida.isEmpty()) {
            try {
                // Format the dates for comparison
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataEntradaLocal = LocalDate.parse(dataEntrada, formatter);
                LocalDate dataSaidaLocal = LocalDate.parse(dataSaida, formatter);

                // Check if exit date is before entry date
                if (dataSaidaLocal.isBefore(dataEntradaLocal)) {
                    JOptionPane.showMessageDialog(rootPane, "A data de saída não pode ser anterior à data de entrada.");
                    return;
                }

                // Calculate the number of days reserved
                int diasReservados = (int) ChronoUnit.DAYS.between(dataEntradaLocal, dataSaidaLocal);

                // Calculate the final price for the reservation
                double valorFinal = (diasReservados * valorQuartoD) + valorServicoD;

                // Check if the room is already reserved
                if (reservaExist(quarto)) {
                    JOptionPane.showMessageDialog(rootPane, "Por algum motivo o quarto N°" + numeroQuarto + " já está reservado.");
                    return;
                } else {
                    // Validate email format
                    if (!isEmailValido(email)) {
                        JOptionPane.showMessageDialog(this, "Email inválido!");
                        return;
                    } else {
                        // Create a new guest and add them to the database
                        var c = new Hospedes(nome, email, cpf, idadeS, quemCadastrou);
                        if (!nome.isEmpty() && !email.isEmpty() && !cpf.isEmpty() && !idadeS.isEmpty()) {
                            // Check if the guest already exists
                            if (hospedeExist(nome, email, cpf)) {
                                JOptionPane.showMessageDialog(rootPane, "Hóspede " + nome + " já está Cadastrado!");
                                return;
                            } else {
                                // Add the guest to the database
                                int idade = Integer.parseInt(idadeS);
                                c.inserirHospede(nome, email, cpf, idade, quemCadastrou);

                                // Create a new reservation and add it to the database
                                var r = new Reservas(nome, quarto, servico, dataEntrada, dataSaida, valorFinal);
                                r.inserirReserva(nome, quarto, servico, dataEntrada, dataSaida, valorFinal);
                                JOptionPane.showMessageDialog(rootPane, "Hóspede " + nome + " reservou o quarto N°" + numeroQuarto + "!");

                                // Update the reservation list in the table
                                listaReservas();

                                // Create a new room reservation and add it to the database
                                var qr = new QuartosReservados(nome, numeroQuarto, dataEntrada, dataSaida, valorFinal);
                                qr.inserirQuartoReservado(nome, numeroQuarto, valorFinal, dataEntrada, dataSaida);

                                // Update room availability to "Indisponível"
                                var q = new Quartos();
                                q.atualizarDisponibilidade(numeroQuarto, "Indisponível");

                                // Clear the text fields
                                cleanTextQ();
                            }
                        }
                    }
                }
            } catch (DateTimeParseException e) {
                // Handle invalid date format
                JOptionPane.showMessageDialog(rootPane, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            }
        } else {
            // Alert if any required field is empty
            JOptionPane.showMessageDialog(rootPane, "Todos os campos obrigatórios devem ser preenchidos.");
        }

    }//GEN-LAST:event_btReservarActionPerformed

    // Function to clear the text fields after reservation
    private void cleanTextQ() {
        edtNome.setText("");  // Clear name field
        edtCPF.setText("");  // Clear CPF field
        edtIdade.setText("");  // Clear age field
        edtEmail.setText("");  // Clear email field

        txtDataEntrada.setText("");  // Clear entry date field
        txtDataSaida.setText("");  // Clear exit date field
    }

    // Function to check if the reservation exists for a given room
    private boolean reservaExist(String quarto) {
        boolean existe = false;
        try (Connection conn = Database.getConnection()) {
            // Query to check if the room is already reserved
            String query = "SELECT COUNT(*) FROM quartos WHERE disponivel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, quarto);  // Set room availability to the parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // If count is greater than 0, the room is reserved
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    // Function to list all current reservations and display them in a table
    private void listaReservas() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM reservas";  // SQL query to fetch all reservations
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Clear the table before displaying new data
            tabelaReservas.setRowCount(0);

            while (rs.next()) {
                // Get data from the database result
                int id = rs.getInt("id");
                String hospede = rs.getString("hospede");
                String quarto = rs.getString("quarto");
                String servicos = rs.getString("servicos");
                String dataEntrada = rs.getString("data_entrada");
                String dataSaida = rs.getString("data_saida");
                double total = rs.getDouble("total");

                // Add the data to the table model (which displays it in the JTable)
                tabelaReservas.addRow(new Object[]{id, hospede, quarto, servicos, dataEntrada, dataSaida, total});
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar as reservas: " + ex.getMessage());
        }
    }

    // Function to list all available rooms and display them in a combo box
    private void listaQuartos() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM quartos WHERE disponivel = 'Disponível'";  // SQL query to get available rooms
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();  // Combo box model to hold room data

            // Loop through result set and add rooms to the combo box
            while (rs.next()) {
                String numeroQuarto = rs.getString("numero");
                String preco = rs.getString("preco");
                model.addElement("Quarto N°" + numeroQuarto + " - R$ " + preco);  // Add formatted room info
            }

            comboQuartos.setModel(model);  // Set the combo box model
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar os quartos: " + ex.getMessage());
        }
    }

    // Function to list all available services and display them in a combo box
    private void listaServicos() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM servicos";  // SQL query to get all services
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();  // Combo box model to hold service data

            // Loop through result set and add services to the combo box
            while (rs.next()) {
                String nomeServico = rs.getString("nome");
                String precoServico = rs.getString("preco");
                model.addElement(nomeServico + " - R$ " + precoServico);  // Add formatted service info
            }

            comboServicos.setModel(model);  // Set the combo box model
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar os serviços: " + ex.getMessage());
        }
    }

    // Function to check if a guest already exists in the database based on their details
    private boolean hospedeExist(String nome, String email, String cpf) {
        boolean existe = false;
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT COUNT(*) FROM hospedes WHERE nome = ? OR email = ? OR cpf = ?";  // SQL query to check if guest exists
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // If count is greater than 0, the guest already exists
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    // Function to validate the email format
    public boolean isEmailValido(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);  // Check if the email matches the regex pattern
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTreservas;
    private javax.swing.JButton btReservar;
    private javax.swing.JComboBox<String> comboQuartos;
    private javax.swing.JComboBox<String> comboServicos;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables
}
