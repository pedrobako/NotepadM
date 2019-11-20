// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.io.IOException;
import java.awt.Dimension;
import modelo.Nota;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import operacao.Login;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import operacao.OperaNotas;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class NotasDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JLabel labelTitulo;
    private JLabel labelNotas;
    private JList<String> listNotas;
    private JButton btnAbrir;
    private JButton btnNova;
    private JButton btnSair;
    
    public NotasDialog(final Frame parent) throws IOException {
        super(parent, "Notas", true);
        OperaNotas.carregaNotas();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cs = new GridBagConstraints();
        cs.fill = 2;
        this.labelTitulo = new JLabel("Notas de " + Login.getLogado().getNome());
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 3;
        panel.add(this.labelTitulo, cs);
        this.labelNotas = new JLabel("Notas: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(this.labelNotas, cs);
        this.listNotas = new JList<String>(OperaNotas.listaNotasUsuario());
        cs.gridx = 1;
        cs.gridy = 3;
        panel.add(this.listNotas, cs);
        (this.btnNova = new JButton("Criar nova")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final EditDialog EdtDlg = new EditDialog(parent, new Nota("Sem titulo", ""));
                EdtDlg.setVisible(true);
                NotasDialog.this.listNotas.setListData(OperaNotas.listaNotasUsuario());
                NotasDialog.this.setSize(300, OperaNotas.listaNotasUsuario().length * 30);
            }
        });
        (this.btnAbrir = new JButton("Abrir")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e1) {
                if (NotasDialog.this.listNotas.getSelectedIndex() > -1) {
                    final EditDialog EdtDlg = new EditDialog(parent, Login.getLogado().getNotas().get(NotasDialog.this.listNotas.getSelectedIndex()));
                    EdtDlg.setVisible(true);
                }
            }
        });
        (this.btnSair = new JButton("Sair")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent eSair) {
                NotasDialog.this.dispose();
            }
        });
        final JPanel bp = new JPanel();
        bp.add(this.btnNova);
        bp.add(this.btnAbrir);
        bp.add(this.btnSair);
        this.getContentPane().add(panel, "Center");
        this.getContentPane().add(bp, "Last");
        this.pack();
        this.setResizable(true);
        this.setMinimumSize(new Dimension(300, 300));
        this.setSize(300, OperaNotas.listaNotasUsuario().length * 30);
        this.setLocationRelativeTo(parent);
    }
}
