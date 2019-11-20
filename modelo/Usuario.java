// 
// Decompiled by Procyon v0.5.36
// 

package modelo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Usuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String nome;
    private String email;
    private String senha;
    private List<Nota> notas;
    
    public Usuario(final String nome, final String email, final String senha) {
        this.notas = new ArrayList<Nota>();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(final String senha) {
        this.senha = senha;
    }
    
    public List<Nota> getNotas() {
        return this.notas;
    }
    
    public void setNotas(final List<Nota> notas) {
        this.notas = notas;
    }
}
