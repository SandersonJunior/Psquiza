package controladores;

import sistemaDePesquisas.Pesquisador;
import validadores.Validador;

import java.util.HashMap;

public class ControladorPesquisador {

	private HashMap<String, Pesquisador> pesquisadores = new HashMap<>();
	private Validador validador = new Validador();

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		validador.validaNome(nome);
		validador.validaEmail(email);
		validador.validaBiografia(biografia);
		validador.validaFoto(foto);
		validador.validaFuncao(funcao);

		Pesquisador novoPesquisador = new Pesquisador(nome, funcao, biografia, email, foto);
		pesquisadores.put(email, novoPesquisador);

	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		validador.validaEmail(email);
		if (!existeAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo invalido");
		}
		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador não encontrado");
		}
		if (atributo.equals("NOME")) {
			validador.validaNome(novoValor);
			pesquisadores.get(email).setNome(novoValor);
		} else if (atributo.equals("FUNCAO")) {
			validador.validaFuncao(novoValor);
			pesquisadores.get(email).setFuncao(novoValor);
		} else if (atributo.equals("BIOGRAFIA")) {
			validador.validaBiografia(novoValor);
			pesquisadores.get(email).setBiografia(novoValor);
		} else if (atributo.equals("EMAIL")) {
			validador.validaEmail(novoValor);
			pesquisadores.get(email).setEmail(novoValor);
		} else if (atributo.equals("FOTO")) {
			validador.validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);
		}
	}

	public Boolean pesquisadorEhAtivo(String email) {
		validador.validaEmail(email);
		if (pesquisadores.get(email).getStatus().equalsIgnoreCase("ativo")) {
			return true;
		}
		return false;
	}

	public String exibePesquisador(String email) {
		validador.validaEmail(email);
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email).toString();
		}
		throw new IllegalArgumentException("Pesquisador nao encontrado");

	}

	public void ativaPesquisador(String email) {
		validador.validaEmail(email);
		if (pesquisadores.get(email).getStatus().equalsIgnoreCase("ativo")) {
			throw new IllegalArgumentException("Pesquisador ja ativo");
		}
	}

	public void desativaPesquisador(String email) {
		if (pesquisadores.get(email).getStatus().equalsIgnoreCase("inativo")) {
			throw new IllegalArgumentException("Pesquisador inativo");
		}
	}

	
	private boolean existeAtributo(String atributo) {
		if (atributo.trim().equalsIgnoreCase("NOME") || atributo.trim().equalsIgnoreCase("BIOGRAFIA")
				|| atributo.trim().equalsIgnoreCase("FOTO") || atributo.trim().equalsIgnoreCase("EMAIL")
				|| atributo.trim().equalsIgnoreCase("FUNCAO")) {
			return true;
		}
		return false;
	}
	public boolean existePesquisador(String email) {
		boolean existe = false;
		if (pesquisadores.containsKey(email)) {
			existe = true;
		}
		return existe;
	}
}