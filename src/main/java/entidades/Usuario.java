package entidades;

import java.util.ArrayList;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private Telefone telefone;
	private ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();
		
	public Usuario() {
		}	
		
	public Usuario(String nome, String email, String senha, String cpf, Telefone telefone, ArrayList<Telefone> listaTelefone) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.listaTelefone = listaTelefone;
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
	
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public ArrayList<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(ArrayList<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}


	

	

	
}
