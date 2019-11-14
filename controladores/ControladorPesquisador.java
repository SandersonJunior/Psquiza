package controladores;

import sistemaDePesquisas.Pesquisador;
import validadores.Validador;

import java.util.HashMap;

//import org.hamcrest.core.Is;
//import org.hamcrest.core.IsNull;

public class ControladorPesquisador {

	private HashMap<String, Pesquisador> pesquisadores = new HashMap<>();
	private Validador validador = new Validador();

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		validador.validaNome(nome);
		validador.validaEmail(email);
		validador.validaBiografia(biografia);
		validador.validaFoto(foto);
		validador.validaFuncao(funcao);

		pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, foto));

	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		validador.validadorStringNula(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.validadorString(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.validaEmail(email);
		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
		if (!existeAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo invalido.");
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
			pesquisadores.put(novoValor, pesquisadores.get(email));
			pesquisadores.get(email).setEmail(novoValor);
			pesquisadores.remove(email);
		} else if (atributo.equals("FOTO")) {
			validador.validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);
		}
	}

	public Boolean pesquisadorEhAtivo(String email) {
		validador.validadorString(email, "Email nao pode ser vazio ou nulo.");
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

	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {

	}
}