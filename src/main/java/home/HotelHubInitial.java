package home;

import DataBase.Database;
import Sexao.Sexsao;
import cadastros.CadastroUser;
import com.formdev.flatlaf.FlatLightLaf;
import cadastros.WinCadastroHospede;
import cadastros.WinCriaQuartos;
import cadastros.WinCriaServicos;
import cadastros.WinReservas;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import logins.LoginUser;

public class HotelHubInitial extends javax.swing.JFrame {

    public HotelHubInitial() {
        initComponents();
        setTitle("Hotelaria");
        Connection conn = Database.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(rootPane, "Liga o XAMPP!");
            System.exit(0);
        }
        setLocationRelativeTo(null);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cadastro = new javax.swing.JMenuItem();
        login = new javax.swing.JMenuItem();

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

        cadastro.setText("Cadastro User");
        cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroActionPerformed(evt);
            }
        });
        jMenu1.add(cadastro);

        login.setText("Login User");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jMenu1.add(login);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1100, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroActionPerformed
        this.dispose();
        JFrame j = new CadastroUser();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_cadastroActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        this.dispose();
        JFrame j = new LoginUser();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_loginActionPerformed

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
            java.util.logging.Logger.getLogger(HotelHubInitial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HotelHubInitial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HotelHubInitial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HotelHubInitial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HotelHubInitial().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cadastro;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem login;
    // End of variables declaration//GEN-END:variables
}
