package sistemaDePesquisas;

import interfaces.Especialidade;
import java.io.Serializable;

public class Pesquisador implements Serializable {
	private Especialidade especialidade;
	private String nome;
	private String biografia;
	private String email;
	private String foto;
	private String funcao;
	private String status;

	public Pesquisador(String nome, String funcao, String biografia, String email, String foto) {
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.funcao = funcao;
		this.status = "ativo";
	}

	public void cadastraEspecialidadeProfessor(String formacao, String unidade, String data) {
		this.especialidade = new Professor(nome, funcao, biografia, email, foto, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(int semestre, double iea) {
		this.especialidade = new Aluno(nome, funcao, biografia, email, foto, semestre, iea);
	}
	
	public String getNome() {
		return nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public String getEmail() {
		return email;
	}

	public String getFuncao() {
		return funcao;
	}

	public String getFoto() {
		return foto;
	}

	public String getStatus() {
		return status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return getNome() + " (" + getFuncao() + ") - " + getBiografia() + " - " + getEmail() + " - " + getFoto();
	}

}