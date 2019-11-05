package sistemaDePesquisas;

public class Problema {
	private String descricao;
	private int viabilidade;
	
	public Problema(String descricao, int viabilidade) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
	}
	
	public String toString() {
		return " - " + descricao + " - " + viabilidade;
	}

}
