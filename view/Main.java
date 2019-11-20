// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.io.IOException;
import java.awt.Frame;

public class Main
{
    public static void main(final String[] args) {
        final LoginDialog loginDlg = new LoginDialog((Frame)null);
        loginDlg.setVisible(true);
        if (loginDlg.isSucceeded()) {
            try {
                final NotasDialog notasDlg = new NotasDialog((Frame)null);
                notasDlg.setVisible(true);
            }
            catch (IOException e) {
                System.out.println("Erro!");
            }
        }
    }
}
