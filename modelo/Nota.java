// 
// Decompiled by Procyon v0.5.36
// 

package modelo;

public class Nota
{
    private String nome;
    private String conteudo;
    
    public Nota(final String nome, final String conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }
    
    public void setNome(final String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }
    
    public String getConteudo() {
        return this.conteudo;
    }
}
