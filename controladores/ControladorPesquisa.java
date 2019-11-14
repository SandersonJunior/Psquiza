package controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import sistemaDePesquisas.Pesquisa;
import validadores.Validador;

public class ControladorPesquisa {

	private Validador validador = new Validador();
	private HashMap<String, Pesquisa> pesquisas = new LinkedHashMap<>();
	private ArrayList<String> objetivos = new ArrayList<>();

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.validadorString(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.validadorTamanhoCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		validador.validadorQuantidadeCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3);
		codigo = geraCodigo(codigo, 1);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;
	}

	private String geraCodigo(String codigo, int i) {
		String cod = codigo + i;
		cod = cod.toUpperCase();
		while (pesquisas.containsKey(cod)) {
			i++;
			cod = codigo + i;
		}
		return cod.toUpperCase();
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (!conteudoASerAlterado.equals("CAMPO") && !conteudoASerAlterado.equals("DESCRICAO")) {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
		if (pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("CAMPO")) {
			validador.validadorTamanhoCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			validador.validadorQuantidadeCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			validador.validadorString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		}
	}

	public void encerraPesquisa(String codigo, String motivo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		validador.validadorString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if (pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		pesquisas.get(codigo).setStatus("desativada");
	}

	public void ativaPesquisa(String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(codigo).getStatus().equals("ativa")) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
		pesquisas.get(codigo).setStatus("ativa");
	}

	public String exibePesquisa(String codigo) {

		if (!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		return pesquisas.get(codigo).toString();
	}

	public boolean pesquisaEhAtiva(String codigo) {
		validador.validadorString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(codigo).getStatus().equals("ativa")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		validador.validadorString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validadorString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalAccessError("Pesquisa desativada.");
		}
		if (pesquisas.get(idPesquisa).getProblema().equals("")) {
			pesquisas.get(idPesquisa).setProblema(idProblema);
			return true;
		} else if (pesquisas.get(idPesquisa).getProblema().equals(idProblema)) {
			return false;
		} else {
			throw new IllegalAccessError("Pesquisa ja associada a um problema.");
		}

	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		validador.validadorString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validadorString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalAccessError("Pesquisa desativada.");
		}
		if (pesquisas.get(idPesquisa).getProblema().equals("")) {
			return false;
		} else if (pesquisas.get(idPesquisa).getProblema().equals(idProblema)) {
			pesquisas.get(idPesquisa).setProblema("");
		}
		return true;

	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validadorString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validadorString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalAccessError("Pesquisa desativada.");
		}
		if (objetivos.contains(idObjetivo) && !pesquisas.get(idPesquisa).getObjetivos().contains(idObjetivo)) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		if (pesquisas.get(idPesquisa).getObjetivos().contains(idObjetivo)) {
			return false;
		} else {
			objetivos.add(idObjetivo);
			pesquisas.get(idPesquisa).getObjetivos().add(idObjetivo);
			return true;
		}

	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validadorString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validadorString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(idPesquisa)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalAccessError("Pesquisa desativada.");
		}
		if (!pesquisas.get(idPesquisa).getObjetivos().contains(idObjetivo)) {
			return false;
		}
		if (pesquisas.get(idPesquisa).getObjetivos().contains(idObjetivo)) {
			pesquisas.get(idPesquisa).getObjetivos().remove(idObjetivo);
			objetivos.remove(idObjetivo);
		}
		return true;

	}

	public String listaPesquisas(String ordem) {
		validador.validadorTipo2(ordem, "Valor invalido da ordem");
		ArrayList<String> maiorValor = new ArrayList<>();
		for (String key : pesquisas.keySet()) {
			maiorValor.add(key);
		}
		String lista = "";
		if (ordem.equals("PROBLEMA")) {
			for (int i = (maiorValor.size())-1; i > -1; i--) {
				if (!pesquisas.get(maiorValor.get(i)).getProblema().equals("")) {
					lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
				}
			}
				for (int i = (maiorValor.size())-1; i > -1; i--) {
					if (pesquisas.get(maiorValor.get(i)).getProblema().equals("")) {
						lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
					}
			
			}
		
	}
		else if(ordem.equals("OBJETIVOS")) {
			String aux = "";
			for (int i = 0; i < (maiorValor.size()); i++) {
				for (int j = 0; j < (maiorValor.size()); j++) {
				if(pesquisas.get(maiorValor.get(i)).getObjetivos().size() < pesquisas.get(maiorValor.get(j)).getObjetivos().size()) {
					aux = maiorValor.get(i);
					maiorValor.set(i, maiorValor.get(j));
					maiorValor.set(j, aux);
				}
			}
				
			}
			for(int i = (maiorValor.size())-1; i > -1; i--) {
				lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
			}
		}
		else if(ordem.equals("PESQUISA")) {
			String aux = "";
			for (int i = 0; i < (maiorValor.size()); i++) {
				for (int j = 0; j < (maiorValor.size()); j++) {
				if(pesquisas.get(maiorValor.get(i)).getObjetivos().size() > pesquisas.get(maiorValor.get(j)).getObjetivos().size()) {
					aux = maiorValor.get(i);
					maiorValor.set(i, maiorValor.get(j));
					maiorValor.set(j, aux);
				}
			}
				
			}
			for(int i = (maiorValor.size())-1; i > -1; i--) {
				lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
			}
		}
		
		return lista.substring(0,lista.length()-3);

}
}