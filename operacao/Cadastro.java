// 
// Decompiled by Procyon v0.5.36
// 

package operacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.List;

public class Cadastro
{
    private static String nomeArquivo;
    private static List<Usuario> cadastrados;
    
    static {
        Cadastro.nomeArquivo = "cadastro.ser";
        Cadastro.cadastrados = carregarCadastro();
    }
    
    public static List<Usuario> getCadastrados() {
        if (Cadastro.cadastrados == null) {
            Cadastro.cadastrados = carregarCadastro();
        }
        return Cadastro.cadastrados;
    }
    
    private static List<Usuario> carregarCadastro() {
        final List<Usuario> cadastro = new ArrayList<Usuario>();
        try {
            final File f = new File(Cadastro.nomeArquivo);
            if (!f.exists()) {
                return cadastro;
            }
            final BufferedReader csvReader = new BufferedReader(new FileReader(Cadastro.nomeArquivo));
            String row;
            while ((row = csvReader.readLine()) != null) {
                final String[] dados = row.split(",");
                cadastro.add(new Usuario(dados[0], dados[1], dados[2]));
            }
            csvReader.close();
        }
        catch (IOException ex) {
            System.out.println("IOException lan\u00e7ada");
        }
        return cadastro;
    }
    
    public static void salvarCadastro() {
        try {
            final File f = new File(Cadastro.nomeArquivo);
            if (!f.exists()) {
                f.createNewFile();
            }
            final FileWriter csvWriter = new FileWriter(Cadastro.nomeArquivo);
            for (final Usuario u : Cadastro.cadastrados) {
                csvWriter.append(String.valueOf(u.getNome()) + ",");
                csvWriter.append(String.valueOf(u.getEmail()) + ",");
                csvWriter.append(String.valueOf(u.getSenha()) + "\n");
            }
            csvWriter.close();
        }
        catch (IOException ex) {
            System.out.println("IOException lan\u00e7ada.");
            ex.printStackTrace();
        }
    }
    
    public static void cadastrarNovo(final String nome, final String email, final String senha) {
        if (Cadastro.cadastrados == null) {
            Cadastro.cadastrados = carregarCadastro();
        }
        Cadastro.cadastrados.add(new Usuario(nome, email, senha));
        salvarCadastro();
    }
}
