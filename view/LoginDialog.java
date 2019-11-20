// 
// Decompiled by Procyon v0.5.36
// 

package view;

import javax.swing.JOptionPane;
import operacao.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JDialog;

public class LoginDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnCadastrar;
    private boolean succeeded;
    private int attempts;
    
    public LoginDialog(final Frame parent) {
        super(parent, "Login", true);
        this.attempts = 0;
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cs = new GridBagConstraints();
        cs.fill = 2;
        this.lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(this.lbUsername, cs);
        this.tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(this.tfUsername, cs);
        this.lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(this.lbPassword, cs);
        this.pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(this.pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        (this.btnLogin = new JButton("Login")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Login.authenticate(LoginDialog.this.getUsername(), LoginDialog.this.getPassword())) {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Hi " + LoginDialog.this.getUsername() + "! You have successfully logged in.", "Login", 1);
                    LoginDialog.access$0(LoginDialog.this, true);
                    LoginDialog.this.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login", 0);
                    LoginDialog.this.tfUsername.setText("");
                    LoginDialog.this.pfPassword.setText("");
                    LoginDialog.access$0(LoginDialog.this, false);
                    final LoginDialog this$0 = LoginDialog.this;
                    LoginDialog.access$4(this$0, this$0.attempts + 1);
                    if (LoginDialog.this.attempts >= 3) {
                        JOptionPane.showMessageDialog(LoginDialog.this, String.valueOf(LoginDialog.this.attempts) + " incorrect attempts. (Waiting for " + LoginDialog.this.attempts + "seconds.", "Login", 0);
                        try {
                            Thread.sleep(LoginDialog.this.attempts * 1000);
                        }
                        catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        });
        (this.btnCancel = new JButton("Cancel")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                LoginDialog.this.dispose();
            }
        });
        (this.btnCadastrar = new JButton("Cadastrar novo...")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final CadastroDialog cadastroDlg = new CadastroDialog(parent);
                cadastroDlg.setVisible(true);
            }
        });
        final JPanel bp = new JPanel();
        bp.add(this.btnLogin);
        bp.add(this.btnCancel);
        bp.add(this.btnCadastrar);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(bp, "Last");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
    }
    
    public String getUsername() {
        return this.tfUsername.getText().trim();
    }
    
    public String getPassword() {
        return new String(this.pfPassword.getPassword());
    }
    
    public boolean isSucceeded() {
        return this.succeeded;
    }
    
    static /* synthetic */ void access$0(final LoginDialog loginDialog, final boolean succeeded) {
        loginDialog.succeeded = succeeded;
    }
    
    static /* synthetic */ void access$4(final LoginDialog loginDialog, final int attempts) {
        loginDialog.attempts = attempts;
    }
}
