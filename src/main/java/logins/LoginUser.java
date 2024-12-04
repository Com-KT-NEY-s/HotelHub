
package logins;

import Database.Database;
import Sexao.Sexsao;
import adm.WinAdmLogado;
import cadastros.CadastroUser;
import home.HotelHubInitial;
import home.HotelHubLogado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class LoginUser extends javax.swing.JFrame {

    // Constructor to initialize the login window and set the closing behavior
    public LoginUser() {
        initComponents();  // Initialize GUI components
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            // When the window is closed, open the initial hotel hub screen
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JFrame j = new HotelHubInitial();
                j.setVisible(true);
                j.setLocationRelativeTo(null);  // Center the window
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btLogin = new javax.swing.JButton();
        edtSenha = new javax.swing.JPasswordField();
        edtCPF = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CPF:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Senha");

        btLogin.setBackground(new java.awt.Color(255, 153, 0));
        btLogin.setForeground(new java.awt.Color(0, 0, 0));
        btLogin.setText("Cadastrar");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        edtSenha.setForeground(new java.awt.Color(0, 0, 0));

        edtCPF.setForeground(new java.awt.Color(0, 0, 0));
        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jMenu1.setText("Usuário");

        user.setText("Cadastro de Usuários");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jMenu1.add(user);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addComponent(btLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtSenha)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(btLogin)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        this.dispose(); // Close current window
        JFrame j = new CadastroUser(); // Open cadastro window
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_userActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        String cpf = edtCPF.getText().trim();  // Get the CPF input from the user
        String senha = new String(edtSenha.getPassword()).trim();  // Get the password input from the user

        // Check if CPF or password is empty
        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");  // Show an error message
            return;
        }

        // Authenticate the user using CPF and password
        Object[] authResult = autenticarUsuario(cpf, senha);
        boolean autenticado = (boolean) authResult[0];  // True if authenticated
        boolean isAdm = (boolean) authResult[1];  // True if the user is an administrator

        // If authenticated, proceed to the correct screen
        if (autenticado) {
            Sexsao.setUsuarioLogado(cpf);  // Set the logged-in user
            this.dispose();  // Close the login window

            JFrame j;
            // Redirect based on user role (Admin or regular user)
            if (isAdm) {
                j = new WinAdmLogado();  // Admin screen
            } else {
                j = new HotelHubLogado();  // Regular user screen
            }
            j.setVisible(true);
            j.setLocationRelativeTo(null);  // Center the window
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");  // Show error if authentication fails
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private Object[] autenticarUsuario(String usuario, String senha) {
        Connection conn = Database.getConnection();  // Get database connection
        Object[] resultado = {false, false};  // First value: authentication status, second: is_adm status

        try {
            // SQL query to check if the user exists and if the password matches
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT is_adm FROM usuarios WHERE (nome = ? OR cpf = ?) AND senha = ?");
            stmt.setString(1, usuario);  // Set username
            stmt.setString(2, usuario);  // Set CPF
            stmt.setString(3, senha);    // Set password

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado[0] = true;  // User authenticated
                resultado[1] = rs.getBoolean("is_adm");  // Set user role (admin or not)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();  // Print exception if any error occurs
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the database connection
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Print exception if closing fails
            }
        }

        return resultado;  // Return the authentication result
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
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
        java.util.logging.Logger.getLogger(LoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(LoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(LoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(LoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new LoginUser().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem user;
    // End of variables declaration//GEN-END:variables
}
