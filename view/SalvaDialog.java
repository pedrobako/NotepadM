// 
// Decompiled by Procyon v0.5.36
// 

package view;

import operacao.OperaNotas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import modelo.Nota;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class SalvaDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JLabel labelNome;
    private JTextField tfNome;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    public SalvaDialog(final Frame parent, final Nota nota) {
        super(parent, "Salvar", true);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cs = new GridBagConstraints();
        cs.fill = 2;
        this.labelNome = new JLabel("Nome");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(this.labelNome, cs);
        this.tfNome = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(this.tfNome, cs);
        (this.btnSalvar = new JButton("Salvar")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent eSalvar) {
                if (SalvaDialog.this.tfNome.getText() != "") {
                    nota.setNome(String.valueOf(SalvaDialog.this.tfNome.getText()) + ".txt");
                    OperaNotas.salvaNota(nota);
                    SalvaDialog.this.dispose();
                }
            }
        });
        (this.btnCancelar = new JButton("Cancelar")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent eCancelar) {
                SalvaDialog.this.dispose();
            }
        });
        final JPanel bp = new JPanel();
        bp.add(this.btnSalvar);
        bp.add(this.btnCancelar);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(bp, "Last");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
    }
}
