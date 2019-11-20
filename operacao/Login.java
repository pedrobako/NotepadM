// 
// Decompiled by Procyon v0.5.36
// 

package operacao;

import modelo.Usuario;

public class Login
{
    private static Usuario logged;
    
    public static boolean authenticate(final String user, final String password) {
        for (final Usuario u : Cadastro.getCadastrados()) {
            if (u.getEmail().equals(user)) {
                final int index = u.getSenha().indexOf("_");
                final String key = u.getSenha().substring(0, index);
                final String salt = u.getSenha().substring(index + 1);
                if (PasswordUtil.verifyPassword(password, key, salt)) {
                    Login.logged = u;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    public static Usuario getLogado() {
        return Login.logged;
    }
}
