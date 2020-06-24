package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidades.Telefone;
import entidades.Usuario;

@ManagedBean(name = "UsuarioBean")
@SessionScoped

public class UsuarioBean {


	private String nome;
	private String email;
	private String senha;
	private String cpf;
	
	private Integer ddd;
	private String numero;
	private String tipo;
	private Boolean erro = Boolean.FALSE;
	
	
	
	private List<Usuario> listaUsuario;
	private List<Usuario> listaUsuario2;
	private Usuario usuario; 
	private UsuarioDAO usuarioDAO;
	private List<Telefone> listaTelefone;
	
 	public UsuarioBean() {
		this.listaUsuario = new ArrayList<Usuario>();
		this.listaUsuario2 = new ArrayList<Usuario>();
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
		
		for (Usuario funcionarioPesquisa : listaUsuario) {

			if (funcionarioPesquisa.getEmail().equals(this.email) && funcionarioPesquisa.getSenha().equals(this.senha)) {

				achou = true;
			}
		}

		if (achou) {
			return "Usuário.xhtml";
		} else {
			setErro(true);
			return "Usuário ou senha inválido";
		}

	}

	public void CadastrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setCpf(cpf);
		

		usuario.setListaTelefone(this.usuario.getListaTelefone());
				
		boolean achou = false;
		
		this.listaUsuario = this.usuarioDAO.listarTodos();
		
		
		for (Usuario usuarioPesquisa : listaUsuario) {
			if (usuarioPesquisa.getNome().equals(this.usuario.getNome())) {
				achou = true;
			}
		}
		
		if(achou == true) {
			FacesMessage faces = new FacesMessage("Usuário já existe!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}else {
			this.usuarioDAO.inserir(usuario);
			this.usuario = new Usuario();	
			FacesMessage faces = new FacesMessage("Usuário cadastrado!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}		
		}		
	
	public void ExcluirUsuario(String pCpf) {
		Usuario usuario = new Usuario();
		
		usuario.setCpf(cpf);
		
		boolean achou = false;
		
		this.listaUsuario = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuario) {
			if (usuarioPesquisa.getCpf().equals(this.getCpf())) {
				achou = true;
			}
		}		
		if(achou == true) {
			this.usuarioDAO.remover(usuario);
			this.usuario = new Usuario();
			FacesMessage faces = new FacesMessage("Usuário Excluído!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
			
		}else {
			FacesMessage faces = new FacesMessage("Usuário não existe!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
	}
	
	
	public void AlterarUsuario() {
				
		usuario.setCpf(cpf);
		boolean achou = false;
		
		this.listaUsuario = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuario) {
			if (usuarioPesquisa.getCpf().equals(cpf)) {
				achou = true;
			}
		}		
		if(achou == true) {
			 this.usuarioDAO.alterar(usuario);
			 FacesMessage faces = new FacesMessage("Alteração Realizada!!!");
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, faces);
			
		}else {
			FacesMessage faces = new FacesMessage("Usuário não existe!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
		}
	
public void  PesquisarUsuario() {
		
		usuario.setCpf(cpf);
		
		boolean achou = false;
		
		this.listaUsuario = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuario) {
			if (usuarioPesquisa.getCpf().equals(cpf)) {
				achou = true;
			}
		}		
		if(achou == true) {
			usuario = this.usuarioDAO.pesquisar(usuario);
			
		}else {
			FacesMessage faces = new FacesMessage("Usuário não existe!!!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
		}

public void  PesquisarUsuario2() {
	
	usuario.setCpf(cpf);
	
	boolean achou = false;
	
	this.listaUsuario2 = this.usuarioDAO.listarTodos2();
	
	for (Usuario usuarioPesquisa : listaUsuario2) {
		if (usuarioPesquisa.getCpf().equals(cpf)) {
			achou = true;
		}
	}		
	if(achou == true) {
		usuario = this.usuarioDAO.pesquisar(usuario);
		
	}else {
		FacesMessage faces = new FacesMessage("Usuário não existe!!!");
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, faces);
	}
	}
	
	public void adicionarTelefoneNaLista() {
		Telefone tel = new Telefone();
		tel.setDdd(ddd);
		tel.setNumero(numero);
		tel.setTipo(tipo);
		usuario.getListaTelefone().add(tel);
		}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
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
	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}
	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	public List<Usuario> getListaUsuario2() {
		return listaUsuario2;
	}
	public void setListaUsuario2(List<Usuario> listaUsuario2) {
		this.listaUsuario2 = listaUsuario2;
	}
		
	
}

