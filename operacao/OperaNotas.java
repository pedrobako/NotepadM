// 
// Decompiled by Procyon v0.5.36
// 

package operacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;
import java.io.FileFilter;
import java.io.File;
import modelo.Nota;
import java.util.ArrayList;

public class OperaNotas
{
//CARREGA NOTAS DE ARQUIVOS
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

//CARREGA NOTAS DO BD
//	public static void carregaNotas() throws SQLException {
//        final List<Nota> notas = new ArrayList<Nota>();
//        Connection con = null;
//        try {
//			con = new Conexao().getConnection();
//			String sql = "select NOME_NOTA, CONTEUDO_NOTA from T_NOTAS_USU_NOTEPADM WHERE EMAIL = ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, Login.getLogado().getEmail());
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			while (rs.next()) {
//				notas.add(new Nota(rs.getString("NOME_NOTA"), rs.getString("CONTEUDO_NOTA")));
//			}
//		} catch (SQLException ex) {
//			System.out.println("SQLException");
//		} finally {
//			con.close();
//		}
//        
//        Login.getLogado().setNotas(notas);
//    }
    
    public static String[] listaNotasUsuario() {
        final String[] listaNotas = new String[Login.getLogado().getNotas().size()];
        int i = 0;
        for (final Nota n : Login.getLogado().getNotas()) {
            listaNotas[i] = n.getNome();
            ++i;
        }
        return listaNotas;
    }
    
//SALVA NOTA EM ARQUIVO    
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
    
//SALVA NOTA NO BD    
//    public static void salvaNota(final Nota nota) throws SQLException {
//    	Connection con = null;
//    	
//    	try {
//    		con = new Conexao().getConnection();
//    		String sql = "select NOME_NOTA FROM T_NOTAS_USU_NOTEPADM where EMAIL = ? and NOME_NOTA = ?";
//    		PreparedStatement stmt = con.prepareStatement(sql);
//    		stmt.setString(1, Login.getLogado().getEmail());
//    		stmt.setString(2, nota.getNome());
//			
//			ResultSet rs = stmt.executeQuery();
//			
//			if (rs.getString("NOME_NOTA").equals(nota.getNome())) {
//				sql = "update T_NOTAS_USU_NOTEPADM set CONTEUDO_NOTA = ? WHERE EMAIL = ? and NOME_NOTA = ?";
//				stmt.setString(1, nota.getConteudo());
//				stmt.setString(2, Login.getLogado().getEmail());
//	    		stmt.setString(3, nota.getNome());
//			} else {
//				sql = "insert into T_NOTAS_USU_NOTEPADM (EMAIL, NOME_NOTA, CONTEUDO_NOTA)"
//						+ "values (?, ?, ?)";
//				stmt.setString(1, Login.getLogado().getEmail());
//				stmt.setString(2, nota.getNome());
//	    		stmt.setString(3, nota.getConteudo());
//			}
//    	} catch (SQLException ex) {
//			System.out.println("SQLException");
//		} finally {
//			con.close();
//		}
//    }
    
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
