// 
// Decompiled by Procyon v0.5.36
// 

package operacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.FileFilter;
import java.io.File;
import modelo.Nota;
import java.util.ArrayList;

public class OperaNotas
{
    public static void carregaNotas() throws IOException {
        final List<Nota> notas = new ArrayList<Nota>();
        final File f = new File(Login.getLogado().getEmail());
        final FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(final File name) {
                return name.getName().toLowerCase().endsWith(".txt");
            }
        };
        final File[] files = f.listFiles(filter);
        if (files != null) {
            File[] array;
            for (int length = (array = files).length, i = 0; i < length; ++i) {
                final File file = array[i];
                notas.add(new Nota(file.getName(), leitorArquivo(file)));
            }
        }
        Login.getLogado().setNotas(notas);
    }
    
    public static String[] listaNotasUsuario() {
        final String[] listaNotas = new String[Login.getLogado().getNotas().size()];
        int i = 0;
        for (final Nota n : Login.getLogado().getNotas()) {
            listaNotas[i] = n.getNome();
            ++i;
        }
        return listaNotas;
    }
    
    public static void salvaNota(final Nota nota) {
        try {
            final File f = new File(String.valueOf(Login.getLogado().getEmail()) + "\\" + nota.getNome());
            if (!f.exists()) {
                f.createNewFile();
            }
            final FileWriter fileWriter = new FileWriter(f.getPath());
            fileWriter.write(nota.getConteudo());
            fileWriter.close();
            carregaNotas();
        }
        catch (IOException e) {
            System.out.println("Exce\u00e7\u00e3o!!!");
        }
    }
    
    public static String leitorArquivo(final File file) throws IOException {
        final BufferedReader buffRead = new BufferedReader(new FileReader(file));
        String linha = "";
        String conteudo = "";
        while (true) {
            linha = buffRead.readLine();
            if (linha == null) {
                break;
            }
            conteudo = String.valueOf(conteudo) + linha + "\n";
        }
        buffRead.close();
        return conteudo;
    }
}
