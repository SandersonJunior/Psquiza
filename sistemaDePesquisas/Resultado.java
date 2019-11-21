package sistemaDePesquisas;

public class Resultado {
	private String resultadoAtividade;
	
	public Resultado(String resultadoAtividade) {
		this.resultadoAtividade = resultadoAtividade;
	}
	@Override
	public String toString() {
		return resultadoAtividade;
	}
}
