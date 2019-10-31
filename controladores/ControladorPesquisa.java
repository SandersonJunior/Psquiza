package controladores;

import java.util.HashMap;

import sistemaDePesquisas.Pesquisa;
import validadores.Validador;

public class ControladorPesquisa {

	private static Validador validador = new Validador();
	private static HashMap<String, Pesquisa> pesquisas = new HashMap<>();

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.validadorString(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.validadorTamanhoCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		validador.validadorQuantidadeCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3);
		codigo = geraCodigo(codigo, 1);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;
	}

	private static String geraCodigo(String codigo, int i) {
		String cod = codigo + i;
		cod = cod.toUpperCase();
		while(pesquisas.containsKey(cod)) {
			i++;
			cod = codigo + i;
		}
		return cod.toUpperCase();
	}
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if(!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if(!conteudoASerAlterado.equals("CAMPO") && !conteudoASerAlterado.equals("DESCRICAO")) {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
		if(pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if(conteudoASerAlterado.equals("CAMPO")) {
			validador.validadorTamanhoCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			validador.validadorQuantidadeCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}
		if(conteudoASerAlterado.equals("DESCRICAO")) {
			validador.validadorString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		}
	}

	public void encerraPesquisa(String codigo, String motivo) {
		if(!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		validador.validadorString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if(pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		pesquisas.get(codigo).setStatus("desativada");
	}

	public void ativaPesquisa(String codigo) {
		if(!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if(pesquisas.get(codigo).getStatus().equals("ativa")) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
	}

	public static String exibePesquisa(String codigo) {
		
		if(!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		return pesquisas.get(codigo).toString();
	}

	public static boolean pesquisaEhAtiva(String codigo) {
		validador.validadorString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if(!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if(pesquisas.get(codigo).getStatus().equals("ativa")) {
			return true;
		}else {
			return false;
		}
	}

}