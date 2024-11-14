package Hotel;

import Classes.Database;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Hotelaria extends javax.swing.JFrame {

    public static void usuarios() {
        JFrame j = new Usuarios();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void quartos() {
        JFrame j = new Quartos();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void reservas() {
        JFrame j = new Reservas();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void hospedes() {
        JFrame j = new Hospedes();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void servicos() {
        JFrame j = new Servicos();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void pagamentos() {
        JFrame j = new Pagamentos();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public Hotelaria() {
        initComponents();
        Connection conn = Database.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(rootPane, "Liga o XAMPP!");
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuUsuarios = new javax.swing.JMenuItem();
        jMenuQuartos = new javax.swing.JMenuItem();
        jMenuHospedes = new javax.swing.JMenuItem();
        jMenuReservas = new javax.swing.JMenuItem();
        jMenuServicos = new javax.swing.JMenuItem();
        jMenuPagamentos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-hoteo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 102, 0));
        jMenuBar1.setForeground(new java.awt.Color(255, 102, 0));

        jMenu1.setText("File");

        jMenuUsuarios.setText("Usuários");
        jMenuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuUsuarios);

        jMenuQuartos.setText("Quartos");
        jMenuQuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuartosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuQuartos);

        jMenuHospedes.setText("Hóspedes");
        jMenuHospedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHospedesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuHospedes);

        jMenuReservas.setText("Reservas");
        jMenuReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReservasActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReservas);

        jMenuServicos.setText("Serviços");
        jMenuServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuServicosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuServicos);

        jMenuPagamentos.setText("Pagamentos");
        jMenuPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPagamentosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuPagamentos);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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

    private void jMenuQuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuartosActionPerformed
        quartos();
    }//GEN-LAST:event_jMenuQuartosActionPerformed

    private void jMenuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuUsuariosActionPerformed
        usuarios();
    }//GEN-LAST:event_jMenuUsuariosActionPerformed

    private void jMenuHospedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHospedesActionPerformed
        hospedes();
    }//GEN-LAST:event_jMenuHospedesActionPerformed

    private void jMenuReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReservasActionPerformed
        reservas();
    }//GEN-LAST:event_jMenuReservasActionPerformed

    private void jMenuServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuServicosActionPerformed
        servicos();
    }//GEN-LAST:event_jMenuServicosActionPerformed

    private void jMenuPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPagamentosActionPerformed
        pagamentos();
    }//GEN-LAST:event_jMenuPagamentosActionPerformed

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
            java.util.logging.Logger.getLogger(Hotelaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotelaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotelaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotelaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotelaria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuHospedes;
    private javax.swing.JMenuItem jMenuPagamentos;
    private javax.swing.JMenuItem jMenuQuartos;
    private javax.swing.JMenuItem jMenuReservas;
    private javax.swing.JMenuItem jMenuServicos;
    private javax.swing.JMenuItem jMenuUsuarios;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
