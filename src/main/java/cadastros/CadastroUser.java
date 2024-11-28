package cadastros;

import Classes.Usuarios;
import Database.Database;
import home.HotelHubInitial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logins.LoginUser;

public class CadastroUser extends javax.swing.JFrame {

    // ADICIONAR FORMATADORES E VALIDAÇÃO
    public CadastroUser() {
        initComponents();
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
        jLabel4 = new javax.swing.JLabel();
        btCadastro = new javax.swing.JButton();
        edtSenha = new javax.swing.JPasswordField();
        edtCPF = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome:");

        edtNome.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Idade:");

        edtIdade.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CPF:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Senha");

        btCadastro.setBackground(new java.awt.Color(255, 153, 0));
        btCadastro.setForeground(new java.awt.Color(0, 0, 0));
        btCadastro.setText("Cadastrar");
        btCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroActionPerformed(evt);
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

        user.setText("Login de Usuários");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNome))
                    .addComponent(btCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtSenha)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCadastro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
        String nome = edtNome.getText().trim();
        String idadeS = edtIdade.getText().trim();
        String cpf = edtCPF.getText().trim();
        String senha = new String(edtSenha.getPassword()).trim();

        // Valida os campos
        if (!validateInputs(nome, idadeS, cpf, senha)) {
            return;
        }

        // Verifica se o CPF já está cadastrado
        if (usuarioExist(cpf)) {
            JOptionPane.showMessageDialog(this, "O CPF " + cpf + " já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cadastra o usuário
        try {
            int idade = Integer.parseInt(idadeS);
            Usuarios u = new Usuarios(nome, idade, cpf, senha);

            if (u.inserirUser(nome, idade, cpf, senha)) {
                JOptionPane.showMessageDialog(this, "Funcionário " + nome + " cadastrado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao converter idade.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btCadastroActionPerformed

    private boolean validateInputs(String nome, String idadeS, String cpf, String senha) {

        if (nome.isEmpty() || idadeS.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (nome.length() < 3) {
            JOptionPane.showMessageDialog(this, "Insira um nome válido.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!idadeS.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int idade = Integer.parseInt(idadeS);
        if (idade < 18) {
            JOptionPane.showMessageDialog(this, "Idade deve ser maior que 18 anos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (cpf.length() < 14 || cpf.length() > 14) {
            JOptionPane.showMessageDialog(this, "Insira um CPF válido.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (senha.length() < 6) {
            JOptionPane.showMessageDialog(this, "A senha deve ter pelo menos 6 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "A senha não pode conter espaços.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        this.dispose(); // Close current window
        JFrame j = new LoginUser(); // Open login window
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_userActionPerformed

    // Check if user already exists in the database
    private static boolean usuarioExist(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Database.getConnection();
            // Verifica se o CPF já está cadastrado
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM usuarios WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0; // Se o COUNT for maior que 0, o CPF já está cadastrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUser.class.getName()).log(Level.SEVERE, null, ex);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastro;
    private javax.swing.JFormattedTextField edtCPF;
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
    // End of variables declaration//GEN-END:variables
}
