package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Telefone;
import entidades.Usuario;
import util.ConexaoMySQL;


public class UsuarioDAOImpl implements UsuarioDAO {

	public void inserir(Usuario usuario) {

		String sql = "insert into usuario (nome, email, senha, cpf)"
				+ "values"
				+ "(?, ?, ?, ?)";
						
		Connection conexao;
		try {
			conexao = ConexaoMySQL.getConexaoMySQL();
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			prep.setString(1, usuario.getNome());
			prep.setString(2, usuario.getEmail());
			prep.setString(3, usuario.getSenha());
			prep.setString(4, usuario.getCpf());
			
			prep.execute();
			
			String sql2 =  "insert into telefone (id, ddd, numero, tipo, cpf_usuario) values (?, ?, ?, ?, ?)";	
			ArrayList <Telefone> listaTel = new ArrayList<Telefone>();
			int i = 0;
			listaTel = usuario.getListaTelefone();
			PreparedStatement prepared = null;
			for(Telefone lista : listaTel) {
				i++;
				prepared = conexao.prepareStatement(sql2);
				prepared.setInt(1, i);
				prepared.setInt(2, lista.getDdd());
				prepared.setString(3, lista.getNumero());
				prepared.setString(4, lista.getTipo());
				prepared.setString(5, usuario.getCpf());
				prepared.execute();
			}	
		
			prep.close();
			prepared.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void alterar(Usuario usuario) {

		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?  where cpf = ?";
		
		Connection conexao;
		try {
			conexao = ConexaoMySQL.getConexaoMySQL();
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			
			prep.setString(1, usuario.getNome());
			prep.setString(2, usuario.getEmail());
			prep.setString(3, usuario.getSenha());
			prep.setString(4, usuario.getCpf());
			prep.execute();
					
			String sql2 = " UPDATE telefone SET  ddd = ?, numero = ?, tipo = ? where cpf_usuario = ? and id = ? ";
			ArrayList <Telefone> listaTel = new ArrayList<Telefone>();
			listaTel = usuario.getListaTelefone();
			int i = 0;
			PreparedStatement prepared = null;
			
			prepared = conexao.prepareStatement(sql2);
			
			for(Telefone lista : listaTel) {
				i++;
				prepared.setInt(1, lista.getDdd());
				prepared.setString(2, lista.getNumero());
				prepared.setString(3, lista.getTipo());
				prepared.setString(4, usuario.getCpf());
				prepared.setInt(5,i);
				
				prepared.executeUpdate();
			}
			
			prep.close();
			prepared.close();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remover(Usuario usuario) {
		Connection conexao = null;
		String sql = "select * from usuario where cpf = ?";	
					
		try {conexao = ConexaoMySQL.getConexaoMySQL();
			PreparedStatement prep = conexao.prepareStatement(sql);
			prep.setString(1, usuario.getCpf());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
				
		try {conexao = ConexaoMySQL.getConexaoMySQL();
		
		String sql1 = "DELETE FROM telefone where cpf_usuario = ?";
			
			PreparedStatement prep1 = conexao.prepareStatement(sql1);
			
			prep1.setString(1, usuario.getCpf());

			prep1.execute();
			prep1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql2 = "DELETE FROM usuario WHERE cpf = ?";

		
		try {
			conexao = ConexaoMySQL.getConexaoMySQL();
			
			PreparedStatement prep2 = conexao.prepareStatement(sql2);
			
			prep2.setString(1, usuario.getCpf());

			prep2.execute();
			prep2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Usuario pesquisar(Usuario usuario) {

		String sql = "select * from usuario where cpf = ?";	
				
		Connection conexao;
		try {
			ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();
			conexao = ConexaoMySQL.getConexaoMySQL();
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			
			prep.setString(1, usuario.getCpf());

			ResultSet result = prep.executeQuery();

			while (result.next()) {
				usuario = new Usuario();
				usuario.setNome(result.getString("NOME"));
				usuario.setEmail(result.getString("EMAIL"));
				usuario.setSenha(result.getString("SENHA"));
				usuario.setCpf(result.getString("CPF"));
			 }
					
			String sql2 = "select * from telefone where cpf_usuario = ?";	
			PreparedStatement prepared = conexao.prepareStatement(sql2);
			prepared.setString(1, usuario.getCpf());
			
			ResultSet result2 = prepared.executeQuery();

			while (result2.next()) {
				Telefone telefone = new Telefone();
				telefone.setDdd(result2.getInt("ddd"));
				telefone.setNumero(result2.getString("numero"));
				telefone.setTipo(result2.getString("tipo"));
				listaTelefone.add(telefone);
			}
			
			usuario.setListaTelefone(listaTelefone);
			
			prep.close();
			prepared.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	
	
	public List<Usuario> listarTodos() {

		String sql = "select * from usuario";
								
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
			
		Connection conexao;
		try {			 
			conexao = ConexaoMySQL.getConexaoMySQL(); 
			
			PreparedStatement prep = conexao.prepareStatement(sql);
			ResultSet result = prep.executeQuery();

			while (result.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setSenha(result.getString("senha"));
				usuario.setCpf(result.getString("cpf"));
				listaUsuario.add(usuario);
			 }			
			prep.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUsuario;
	}
	
	public List<Usuario> listarTodos2() {

		String sql = " select * from usuario inner join telefone tel on usuario.cpf = tel.cpf_usuario order by nome ";
				
		List<Usuario> listaUsuario2 = new ArrayList<Usuario>();
				
		Connection conexao;
		try {			
			 
			conexao = ConexaoMySQL.getConexaoMySQL(); 			
			PreparedStatement prep = conexao.prepareStatement(sql);
			ResultSet result = prep.executeQuery();

			while (result.next()) {
				
				Usuario usuario = new Usuario();
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setSenha(result.getString("senha"));
				usuario.setCpf(result.getString("cpf"));
								
				Telefone telefone = new Telefone();
				telefone.setDdd(result.getInt("ddd"));
				telefone.setNumero(result.getString("numero"));
				telefone.setTipo(result.getString("tipo"));
				usuario.setTelefone(telefone);		
				listaUsuario2.add(usuario);
			 }
				prep.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaUsuario2;
		}
			
}
