package entidades;

public class Telefone {
	private Integer ddd;
	private String numero;
	private String tipo;
	private String cpf_usuario;
	private Integer idTel;
	

	public Telefone() {
	}

	public Telefone(Integer ddd, String numero, String tipo, String cpf_usuario) {
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.cpf_usuario = cpf_usuario;
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

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}	
	
	public Integer getIdTel() {
		return idTel;
	}

	public void setIdTel(Integer idTel) {
		this.idTel = idTel;
	}
	
}