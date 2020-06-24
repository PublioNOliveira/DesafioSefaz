
package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidades.Usuario;

@ManagedBean(name = "LoginBean")
@ViewScoped

public class LoginBean {
	private String email;
	private String senha;
		
	private List<Usuario> listaUsuario;
	private Usuario usuario; 
	private UsuarioDAO usuarioDAO;
	private Boolean erro = Boolean.FALSE;

	
	public LoginBean() {
		this.listaUsuario = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuarioDAO = new UsuarioDAOImpl();
		
		this.erro = Boolean.FALSE;
	}

	@PostConstruct
 	public void init() {
 		setErro(false);
 		usuario.getListaTelefone().clear();
 		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 	}

	public String entrar() {

		boolean achou = false;
		
		this.listaUsuario = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuario) {

			if (usuarioPesquisa.getEmail().equals(this.email) && usuarioPesquisa.getSenha().equals(this.senha)) {

				achou = true;
			}
		}

		if (achou) {
			 return "Usuarios?faces-redirect=true";
		} else {
			setErro(true);
			return "Usuário ou senha inválida";
		}

	}

		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Boolean getErro() {
		return erro;
	}


	public void setErro(Boolean erro) {
		this.erro = erro;
	}


}

