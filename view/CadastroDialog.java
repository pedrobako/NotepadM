// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.io.File;
import javax.swing.JOptionPane;
import operacao.Cadastro;
import operacao.PasswordUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class CadastroDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JLabel lbEmail;
    private JTextField tfEmail;
    private JLabel lbNome;
    private JTextField tfNome;
    private JLabel lbPassword;
    private JPasswordField tfPassword;
    private JLabel lbPassConf;
    private JPasswordField tfPassConf;
    private JButton btnCadastrar;
    private JButton btnCancelar;
    
    public CadastroDialog(final Frame parent) {
        super(parent, "Cadastro", true);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cs = new GridBagConstraints();
        cs.fill = 2;
        this.lbNome = new JLabel("Nome: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(this.lbNome, cs);
        this.tfNome = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(this.tfNome, cs);
        this.lbEmail = new JLabel("Email: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(this.lbEmail, cs);
        this.tfEmail = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(this.tfEmail, cs);
        this.lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(this.lbPassword, cs);
        this.tfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(this.tfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        this.lbPassConf = new JLabel("Password (conf): ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        panel.add(this.lbPassConf, cs);
        this.tfPassConf = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(this.tfPassConf, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        (this.btnCadastrar = new JButton("Cadastrar")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String pass = new String(CadastroDialog.this.tfPassword.getPassword());
                final String conf = new String(CadastroDialog.this.tfPassConf.getPassword());
                if (pass.equals(conf)) {
                    final String salt = PasswordUtil.generateSalt(512).get();
                    pass = PasswordUtil.hashPassword(pass, salt).get();
                    Cadastro.cadastrarNovo(CadastroDialog.this.tfNome.getText(), CadastroDialog.this.tfEmail.getText(), String.valueOf(pass) + "_" + salt);
                    JOptionPane.showMessageDialog(CadastroDialog.this, "Cadastro realizado com sucesso!", "Cadastro", 1);
                    new File(CadastroDialog.this.tfEmail.getText()).mkdir();
                    CadastroDialog.this.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(CadastroDialog.this, "Senhas n\u00e3o conferem.", "Cadastro", 0);
                }
            }
        });
        (this.btnCancelar = new JButton("Cancelar")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CadastroDialog.this.dispose();
            }
        });
        final JPanel bp = new JPanel();
        bp.add(this.btnCadastrar);
        bp.add(this.btnCancelar);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(bp, "Last");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
    }
}
