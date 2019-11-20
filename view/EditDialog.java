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
import javax.swing.JTextArea;
import javax.swing.JDialog;

public class EditDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JTextArea taNota;
    private JButton btnSalvar;
    private JButton btnSair;
    
    public EditDialog(final Frame parent, final Nota nota) {
        super(parent, nota.getNome(), true);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cs = new GridBagConstraints();
        cs.fill = 2;
        (this.taNota = new JTextArea(nota.getConteudo(), 30, 50)).setEditable(true);
        this.taNota.setLineWrap(true);
        panel.add(this.taNota);
        (this.btnSalvar = new JButton("Salvar")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent eSalvar) {
                nota.setConteudo(EditDialog.this.taNota.getText());
                if (nota.getNome() == "Sem titulo") {
                    final SalvaDialog salvaDlg = new SalvaDialog(parent, nota);
                    salvaDlg.setVisible(true);
                    EditDialog.this.setTitle(nota.getNome());
                }
                else {
                    OperaNotas.salvaNota(nota);
                }
            }
        });
        (this.btnSair = new JButton("Sair")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent eSair) {
                EditDialog.this.dispose();
            }
        });
        final JPanel bp = new JPanel();
        bp.add(this.btnSalvar);
        bp.add(this.btnSair);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(bp, "Last");
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
    }
}
