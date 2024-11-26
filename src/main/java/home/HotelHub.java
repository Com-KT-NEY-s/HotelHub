package home;

import Sexao.Sexsao;
import com.formdev.flatlaf.FlatLightLaf;
import cadastros.WinCadastroHospede;
import cadastros.WinCriaQuartos;
import cadastros.WinCriaServicos;
import cadastros.WinReservas;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class HotelHub extends javax.swing.JFrame {

    public HotelHub() {
        initComponents();
        setTitle(Sexsao.getUsuarioLogado() + " na questão!");
        setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JFrame j = new HotelHubInitial();
                j.setVisible(true);
                j.setLocationRelativeTo(null);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        hospedes = new javax.swing.JMenuItem();
        quartos = new javax.swing.JMenuItem();
        reservas = new javax.swing.JMenuItem();
        servicos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        desconectar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\WESLEYLUCASMOREIRA\\Documents\\logo muito foda.jpg")); // NOI18N

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

        hospedes.setText("Hóspedes");
        hospedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospedesActionPerformed(evt);
            }
        });
        jMenu1.add(hospedes);

        quartos.setText("Quartos");
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

        servicos.setText("Servicos");
        servicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicosActionPerformed(evt);
            }
        });
        jMenu1.add(servicos);

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

    private void hospedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospedesActionPerformed
        this.dispose();
        JFrame j = new WinCadastroHospede();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_hospedesActionPerformed

    private void quartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartosActionPerformed
        this.dispose();
        JFrame j = new WinCriaQuartos();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_quartosActionPerformed

    private void reservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservasActionPerformed
        this.dispose();
        JFrame j = new WinReservas();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_reservasActionPerformed

    private void servicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicosActionPerformed
        this.dispose();
        JFrame j = new WinCriaServicos();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_servicosActionPerformed

    private void desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja desconectar " + Sexsao.getUsuarioLogado() +"?", "Desconectar", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            // Mensagem de confirmação
            JOptionPane.showMessageDialog(rootPane, "Desconectado com sucesso!");

            // Limpa a tela atual (HomeLogado)
            this.dispose();

            // Chama a tela inicial de login
            JFrame j = new HotelHubInitial();  // Certifique-se de que a classe Initial seja a tela de login
            j.setVisible(true);
            j.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_desconectarActionPerformed

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
            java.util.logging.Logger.getLogger(HotelHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelHub().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem desconectar;
    private javax.swing.JMenuItem hospedes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem quartos;
    private javax.swing.JMenuItem reservas;
    private javax.swing.JMenuItem servicos;
    // End of variables declaration//GEN-END:variables
}
