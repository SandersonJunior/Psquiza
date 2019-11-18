package sistemaDePesquisas;

import interfaces.Especialidade;

public class Professor extends Pesquisador implements Especialidade {
	private String formacao;
	private String unidade;
	private String data;

	public Professor(String nome, String funcao, String biografia, String email, String foto, String formacao,
			String unidade, String data) {
		super(nome, funcao, biografia, email, foto);
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;

	}

	public String getFormacao() {
		return formacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + getFormacao() + " - " + getUnidade() + " - " + getData();
	}

}
