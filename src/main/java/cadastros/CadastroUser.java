package cadastros;

import Classes.Usuarios;
import DataBase.Database;
import home.HotelHubInitial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import logins.LoginUser;

public class CadastroUser extends javax.swing.JFrame {

    public CadastroUser() {
        initComponents();
        formatarCampoCPF(edtCPF);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JFrame j = new HotelHubInitial();
                j.setVisible(true);
                j.setLocationRelativeTo(null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btCadastro = new javax.swing.JButton();
        edtSenha = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user = new javax.swing.JMenuItem();
        voltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome:");

        jLabel2.setText("Idade:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Senha");

        btCadastro.setText("Cadastrar");
        btCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroActionPerformed(evt);
            }
        });

        jMenu1.setText("Usuário");

        user.setText("Login de Usuários");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jMenu1.add(user);

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        jMenu1.add(voltar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btCadastro)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtSenha))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btCadastro)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
        String nome = edtNome.getText();
        String idadeS = edtIdade.getText();
        String cpf = edtCPF.getText();
        String senha = new String(edtSenha.getPassword());

        var c = new Usuarios(nome, idadeS, cpf, senha);
        if (!nome.isEmpty() && !idadeS.isEmpty() && !cpf.isEmpty() && !senha.isEmpty()) {
            if (usuarioExist(nome, cpf)) {
                JOptionPane.showMessageDialog(rootPane, "Usuário " + nome + " já está Cadastrado!");
                return;
            } else {
                int idade = Integer.parseInt(idadeS);
                c.inserirUser(nome, idade, cpf, senha);
                JOptionPane.showMessageDialog(rootPane, "Usuário " + nome + " foi Adicionado com Sucesso!");

            }
        }
    }//GEN-LAST:event_btCadastroActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        this.dispose();
        JFrame j = new LoginUser();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_userActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        this.dispose();
        JFrame j = new HotelHubInitial();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_voltarActionPerformed
    private static boolean usuarioExist(String nome, String cpf) {
        Connection conn = Database.getConnection();
        boolean existe = false;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM usuarios WHERE nome = ? OR cpf = ?");
            stmt.setString(1, nome);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }

    private void formatarCampoCPF(JTextField campo) {
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');

            // Criamos um JFormattedTextField temporário com a máscara
            JFormattedTextField formattedField = new JFormattedTextField(cpfMask);
            formattedField.setText(campo.getText());  // Copia o texto existente (se houver)

            // Substituímos o campo original pelo campo formatado
            formattedField.setColumns(campo.getColumns());
            formattedField.setBounds(campo.getBounds());
            formattedField.setFont(campo.getFont());

            // Remove o JTextField atual e adiciona o JFormattedTextField formatado no seu lugar
            getContentPane().remove(campo);
            getContentPane().add(formattedField);
            getContentPane().revalidate();
            getContentPane().repaint();

            // Atualiza a referência para o novo campo formatado
            this.edtCPF = formattedField;

        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao aplicar formatação ao CPF.");
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastro;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem user;
    private javax.swing.JMenuItem voltar;
    // End of variables declaration//GEN-END:variables
}
