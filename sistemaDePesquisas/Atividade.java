package sistemaDePesquisas;

import java.util.HashMap;

import sistemaDePesquisas.Item;

public class Atividade {
	private String descricao;
	private int duracao;
	private String descricaoRisco;
	private String nivelRisco;
	//private HashMap<String, Item> itensAtividade;

	public Atividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.descricao = descricao;
		this.descricaoRisco = descricaoRisco;
		this.nivelRisco = nivelRisco;
		//this.itensAtividade = new HashMap<>();
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}


	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	public void setDescricaoRisco(String descricaoRisco) {
		this.descricaoRisco = descricaoRisco;
	}

	public String getNivelRisco() {
		return nivelRisco;
	}

	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	@Override
	public String toString() {
		return getDescricao() + " (" + getNivelRisco() + " - " + getDescricaoRisco() + ")";
	}

}
