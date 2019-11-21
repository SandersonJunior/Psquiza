package sistemaDePesquisas;

import java.util.ArrayList;
import java.io.Serializable;

public class Problema implements Serializable {
	private String descricao;
	private int viabilidade;
	private ArrayList<String> pesquisas;
	
	public Problema(String descricao, int viabilidade) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.pesquisas = new ArrayList<String>();
	}
	
	
	public String getDescricao() {
		return descricao;
	}


	public int getViabilidade() {
		return viabilidade;
	}


	public ArrayList<String> getPesquisas() {
		return pesquisas;
	}


	public String toString() {
		return " - " + descricao + " - " + viabilidade;
	}

}
