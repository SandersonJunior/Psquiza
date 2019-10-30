package controladores;

import java.util.HashMap;

import sistemaDePesquisas.Pesquisa;

public class ControladorPesquisa {
	private static HashMap<String, Pesquisa> pesquisas = new HashMap<>();

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		String codigo = campoDeInteresse.substring(0, 2);
		codigo = geraCodigo(codigo, 1);
		Pesquisa p = new Pesquisa(descricao, campoDeInteresse, codigo);
		pesquisas.put(codigo, p);
		return codigo;
	}

	private static String geraCodigo(String codigo, int i) {
		if (pesquisas.containsKey((codigo + i))) {
			i++;
			geraCodigo(codigo, i);
			codigo = codigo + i;
		}
		return codigo;
	}
	public static void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {

	}

	public static void encerraPesquisa(String codigo, String motivo) {

	}

	public static void ativaPesquisa(String codigo) {

	}

	public static String exibePesquisa(String codigo) {
		return null;
	}

	public static boolean pesquisaEhAtiva(String codigo) {
		return false;
	}

}
