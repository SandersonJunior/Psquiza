package sistemaDePesquisas;

import java.io.Serializable;

public class Objetivo implements Serializable {
	private String tipo;
	private String descricao;
	private int aderencia;
	private int viabilidade;
	
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;		
	}
	
	public String toString() {
		int valor = aderencia + viabilidade;
		return " - " + tipo + " - " + descricao + " - " + valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
