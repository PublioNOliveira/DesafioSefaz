package dao;

import java.util.List;

import entidades.Usuario;

public interface UsuarioDAO {

	public void inserir(Usuario funcionario);
	
	public void alterar(Usuario funcionario);

	public void remover(Usuario funcionario);

	public Usuario pesquisar(Usuario funcionario);

	public List<Usuario> listarTodos();
	
	public List<Usuario> listarTodos2();
	}
