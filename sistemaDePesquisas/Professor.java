package sistemaDePesquisas;

public class Professor {
	private String formacao;
	private String unidade;
	private String data;

	public Professor(String nome, String biografia, String email, String foto, String funcao, String formacao,
			String unidade, String data) {
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
