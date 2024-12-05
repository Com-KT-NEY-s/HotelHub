package cadastros;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class WinQuartosReservados extends javax.swing.JFrame {

    // DefaultTableModel to hold the list of reserved rooms
    private DefaultTableModel tabelaQuartosReservados = new DefaultTableModel(new Object[]{"ID", "Hóspede", "Quarto", "Valor", "Entrada", "Saída"}, 0);

    // Constructor: Initialize the window and load the reserved rooms
    public WinQuartosReservados() {
        initComponents();
        setTitle("Quartos Reservados");
        listaQuartosReservados();  // Populate the table with reserved rooms
        setLocationRelativeTo(null);  // Center the window

        // Listener for changes in the table
        tabelaQuartosReservados.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Check if the table data was updated
                    int row = e.getFirstRow();  // Get the updated row index

                    // Get the updated values from the row
                    int id = Integer.parseInt(tabelaQuartosReservados.getValueAt(row, 0).toString());  // ID (first column)
                    String hospede = tabelaQuartosReservados.getValueAt(row, 1).toString();  // Guest (second column)
                    String quarto = tabelaQuartosReservados.getValueAt(row, 2).toString().replace("N°", "");  // Room number (remove "N°")
                    String valor = tabelaQuartosReservados.getValueAt(row, 3).toString().replace("R$", "");  // Price (remove "R$")
                    String entrada = tabelaQuartosReservados.getValueAt(row, 4).toString();  // Check-in date
                    String saida = tabelaQuartosReservados.getValueAt(row, 5).toString();  // Check-out date

                    // Update the database with the modified row data
                    atualizarPelaTabelaQR(id, hospede, quarto, valor, entrada, saida);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        quartosReservados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        quartosReservados.setModel(tabelaQuartosReservados);
        jScrollPane2.setViewportView(quartosReservados);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("QUARTOS RESERVADOS");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\WESLEYLUCASMOREIRA\\Documents\\mini hotel.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 251, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Method to update room reservation data in the database
    private static void atualizarPelaTabelaQR(int id, String hospede, String quarto, String preco, String dataEntrada, String dataSaida) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update room reservation
            String query = "UPDATE quartosreservados SET hospede = ?, quarto = ?, valor = ?, data_entrada = ?, data_saida = ? WHERE id_quartoReservado = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, hospede);
            stmt.setString(2, quarto);
            stmt.setDouble(3, Double.parseDouble(preco));  // Parse the price to double
            stmt.setString(4, dataEntrada);
            stmt.setString(5, dataSaida);
            stmt.setInt(6, id);  // Set the reservation ID

            stmt.executeUpdate();  // Execute the update query
            System.out.println("Dados atualizados no banco de dados!");  // Print success message
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados: " + ex.getMessage());  // Show error if something goes wrong
        }
    }

    // Method to populate the table with reserved rooms data from the database
    public void listaQuartosReservados() {
        Connection conn = Database.getConnection();
        try {
            // SQL query to fetch the reserved rooms
            String sql = "SELECT id_quartoReservado, hospede, quarto, valor, data_entrada, data_saida FROM quartosreservados";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();  // Execute the query

            tabelaQuartosReservados.setRowCount(0);  // Clear the table before populating it

            // Iterate over the result set and add rows to the table
            while (rs.next()) {
                int id_QR = rs.getInt("id_quartoReservado");
                String hospede = rs.getString("hospede");
                String quarto = rs.getString("quarto");
                double valor = rs.getDouble("valor");
                String dataEntrada = rs.getString("data_entrada");
                String dataSaida = rs.getString("data_saida");

                // Add the row to the table
                tabelaQuartosReservados.addRow(new Object[]{id_QR, hospede, "N°" + quarto, "R$" + valor, dataEntrada, dataSaida});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new WinQuartosReservados().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable quartosReservados;
    // End of variables declaration//GEN-END:variables
}
