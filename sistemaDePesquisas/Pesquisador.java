package sistemaDePesquisas;

public class Pesquisador {
	private String nome;
	private String biografia;
	private String email;
	private String foto;
	private String funcao;
	private String status;

	public Pesquisador(String nome, String biografia, String email, String foto, String funcao) {

		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.funcao = funcao;
		this.status = "ativo";
	}

	public String getStatus() {
		return status;
	}
	// public String setStatus(){
	// this.status = status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return nome + "(" + funcao + ") - "  + biografia + " - " + email + " - " + foto;
	}
}