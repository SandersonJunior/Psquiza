package sistemaDePesquisas;

import java.util.HashMap;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String codigo;
	
	private HashMap<String, Pesquisa> codigos = new HashMap<>();

	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
	}

}
