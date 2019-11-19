package controladores;

import sistemaDePesquisas.Pesquisador;
import sistemaDePesquisas.Professor;
import validadores.Validador;

import java.util.HashMap;
import java.util.Iterator;

//import org.hamcrest.core.Is;
//import org.hamcrest.core.IsNull;

public class ControladorPesquisador {
	private HashMap<String, Pesquisador> pesquisadores = new HashMap<>();
	private Validador validador = new Validador();

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		validador.validaString(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.validaString(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.validaString(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.validaEmail(email);
		validador.validaFoto(foto);

		pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, foto));

	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		validador.validaString(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}

		if (!existeAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo invalido.");
		}

		if (atributo.equals("NOME")) {
			validador.validaString(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			pesquisadores.get(email).setNome(novoValor);

		} else if (atributo.equals("FUNCAO")) {
			validador.validaString(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			pesquisadores.get(email).setFuncao(novoValor);

		} else if (atributo.equals("BIOGRAFIA")) {
			validador.validaString(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			pesquisadores.get(email).setBiografia(novoValor);

		} else if (atributo.equals("FOTO")) {
			validador.validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);

		} else if (atributo.equals("EMAIL")) {
			validador.validaEmail(novoValor);
			pesquisadores.put(novoValor, pesquisadores.get(email));
			pesquisadores.get(email).setEmail(novoValor);
			pesquisadores.remove(email);
		}
	}

	public Boolean pesquisadorEhAtivo(String email) {
		validador.validaString(email, "Email nao pode ser vazio ou nulo.");
		validador.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NullPointerException("Pesquisador nao encontrado");
		}

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

		if (!pesquisadores.containsKey(email)) {
			throw new NullPointerException("Pesquisador nao encontrado");
		}

		if (pesquisadores.get(email).getStatus().equals("ativo")) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}

		pesquisadores.get(email).setStatus("ativo");
	}

	public void desativaPesquisador(String email) {
		validador.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NullPointerException("Pesquisador nao encontrado");
		}

		if (pesquisadores.get(email).getStatus().equalsIgnoreCase("inativo")) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		pesquisadores.get(email).setStatus("inativo");
	}

	private boolean existeAtributo(String atributo) {
		if (atributo.equals("NOME") || atributo.equals("BIOGRAFIA") || atributo.equals("FOTO")
				|| atributo.equals("EMAIL") || atributo.equals("FUNCAO")) {
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

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.validaProfessor(email, formacao, unidade, data);

		if (existePesquisador(email)) {
			this.pesquisadores.get(email).cadastraEspecialidadeProfessor(formacao, unidade, data);
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
		validador.validaAluno(email, semestre, iea);

		if (existePesquisador(email)) {
			this.pesquisadores.get(email).cadastraEspecialidadeAluno(semestre, iea);
		} else {
			throw new IllegalArgumentException("Pesquisadora nao encontrada.");
		}
	}
}
