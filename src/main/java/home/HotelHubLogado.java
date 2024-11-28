package home;

import Sexao.Sexsao;
import com.formdev.flatlaf.FlatLightLaf;
import cadastros.WinQuartosServicos;
import cadastros.WinQuartosReservados;
import cadastros.WinReservas;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class HotelHubLogado extends javax.swing.JFrame {

    public HotelHubLogado() {
        initComponents(); // Initialize UI components
        setTitle("Funcionário: " + Sexsao.getNomePorCpf());
        setLocationRelativeTo(null);

        // Add a window listener for when the window is closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                desconect();
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        quartos = new javax.swing.JMenuItem();
        reservas = new javax.swing.JMenuItem();
        quartosReservados = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        desconectar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel3)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel3)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jMenu1.setText("HotelHub");

        quartos.setText("Quartos e Serviços");
        quartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartosActionPerformed(evt);
            }
        });
        jMenu1.add(quartos);

        reservas.setText("Reservas");
        reservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservasActionPerformed(evt);
            }
        });
        jMenu1.add(reservas);

        quartosReservados.setText("Quartos Reservados");
        quartosReservados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartosReservadosActionPerformed(evt);
            }
        });
        jMenu1.add(quartosReservados);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sair");

        desconectar.setText("Desconectar");
        desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarActionPerformed(evt);
            }
        });
        jMenu2.add(desconectar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1100, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartosActionPerformed
        JFrame j = new WinQuartosServicos(); // Open room management
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_quartosActionPerformed

    private void reservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservasActionPerformed
        JFrame j = new WinReservas(); // Open reservation management
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_reservasActionPerformed

    private void desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarActionPerformed
        desconect();
    }//GEN-LAST:event_desconectarActionPerformed

    private void quartosReservadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartosReservadosActionPerformed
        JFrame j = new WinQuartosReservados(); // Open service management
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_quartosReservadosActionPerformed

    public void desconect() {
        int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja desconectar?", "Desconectar", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            // Mensagem de confirmação
            JOptionPane.showMessageDialog(rootPane, "Desconectado com sucesso!");

            // Chama a tela inicial de login
            JFrame j = new HotelHubInitial();
            j.setVisible(true);
            j.setLocationRelativeTo(null);
        }else{
            // Chama a tela logado
            JFrame j = new HotelHubLogado();
            j.setVisible(true);
            j.setLocationRelativeTo(null);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelHubLogado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem desconectar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem quartos;
    private javax.swing.JMenuItem quartosReservados;
    private javax.swing.JMenuItem reservas;
    // End of variables declaration//GEN-END:variables
}
