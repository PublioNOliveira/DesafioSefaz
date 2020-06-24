package test;

import java.util.List;

import org.junit.Test;

import dao.UsuarioDAOImpl;
import entidades.Usuario;
import junit.framework.TestCase;

public class UsuarioDAOImplTeste extends TestCase {

	protected void setUp() throws Exception {
		
	}
	

	@Test	
	public void testListarTodos() {
		
		UsuarioDAOImpl usuario = new UsuarioDAOImpl();
		List<Usuario> listaUsuario = usuario.listarTodos();
		System.out.println("TESTE LISTAR TODOS");
		for(Usuario user : listaUsuario) {
			System.out.println("Nome: " + user.getNome());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Senha: " + user.getSenha());
			System.out.println("Cpf: " + user.getCpf());
			System.out.println("-----------------------------------");
		}
		
	}
	
	@Test
	public void testListarTodos2() {
		UsuarioDAOImpl usuario2 = new UsuarioDAOImpl();
		List<Usuario> listaUsuario2 = usuario2.listarTodos2();		
		
		System.out.println("TESTE LISTAR TODOS2");
		for(Usuario user : listaUsuario2) {
			System.out.println("Nome: " + user.getNome());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Senha: " + user.getSenha());
			System.out.println("Cpf: " + user.getCpf());
			System.out.println("DDD: " + user.getTelefone().getDdd());
			System.out.println("Número: " + user.getTelefone().getNumero());
			System.out.println("Tipo: " + user.getTelefone().getTipo());
			System.out.println("-----------------------------------");
			}
		
		
	}

}
