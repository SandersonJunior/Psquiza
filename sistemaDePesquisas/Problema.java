package sistemaDePesquisas;

import java.util.ArrayList;

public class Problema {
	private String descricao;
	private int viabilidade;
	private ArrayList<String> pesquisas;
	
	public Problema(String descricao, int viabilidade) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.pesquisas = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getPesquisas() {
		return pesquisas;
	}


	public String toString() {
		return " - " + descricao + " - " + viabilidade;
	}

}
