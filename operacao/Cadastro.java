package operacao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.List;

public class Cadastro {
	private static String nomeArquivo;
	private static List<Usuario> cadastrados;

	static {
		Cadastro.nomeArquivo = "cadastro.ser";
		try {
			Cadastro.cadastrados = carregarCadastroBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Usuario> getCadastrados() {
		try {
			cadastrados = carregarCadastroBD();
			//cadastrados = carregarCadastroArquivo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Cadastro.cadastrados;
	}

//	private static List<Usuario> carregarCadastroArquivo() {
//		final List<Usuario> cadastro = new ArrayList<Usuario>();
//		try {
//			final File f = new File(Cadastro.nomeArquivo);
//			if (!f.exists()) {
//				return cadastro;
//			}
//			final BufferedReader csvReader = new BufferedReader(new FileReader(Cadastro.nomeArquivo));
//			String row;
//			while ((row = csvReader.readLine()) != null) {
//				final String[] dados = row.split(",");
//				cadastro.add(new Usuario(dados[0], dados[1], dados[2]));
//			}
//			csvReader.close();
//		} catch (IOException ex) {
//			System.out.println("IOException lan\u00e7ada");
//		}
//		return cadastro;
//	}
	
	private static List<Usuario> carregarCadastroBD() throws SQLException {
		final List<Usuario> cadastro = new ArrayList<Usuario>();
		Connection con = null;
		try {
			con = new Conexao().getConnection();
			String sql = "select NOME,EMAIL,SENHA from T_CADASTRO_USU_NOTEPADM";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				cadastro.add(new Usuario(rs.getString("NOME"), rs.getString("EMAIL"), rs.getString("SENHA")));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException");
		} finally {
			con.close();
		}
		return cadastro;
	}

	public static void cadastrarNovo(final String nome, final String email, final String senha) {
		getCadastrados();
		Cadastro.cadastrados.add(new Usuario(nome, email, senha));
		salvarCadastroArquivo();
		try {
			cadastraBD(nome, email, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void cadastraBD(String nome, String email, String senha) throws SQLException {
		String sql = "insert into T_CADASTRO_USU_NOTEPADM " + "(NOME,EMAIL,SENHA) " + "values (?,?,?)";
		Connection con = null;
		try {
			con = new Conexao().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, senha);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	public static void salvarCadastroArquivo() {
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
		} catch (IOException ex) {
			System.out.println("IOException lan\u00e7ada.");
			ex.printStackTrace();
		}
	}
}