package controladores;

import java.util.HashMap;

import java.io.Serializable;
import sistemaDePesquisas.Objetivo;
import validadores.Validador;

public class ControladorObjetivo implements Serializable {
	private Validador validador = new Validador();
	private HashMap<String, Objetivo> objetivosCadastrados = new HashMap<>();

	int cont = 0;

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.validaString(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.validaTipoObjetivo(tipo, "Valor invalido de tipo.");
		validador.validaString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validaViabilidade(viabilidade, "Valor invalido de viabilidade.");
		validador.validaAderencia(aderencia, "Valor invalido de aderencia");

		cont += 1;
		objetivosCadastrados.put("O" + Integer.toString(cont), new Objetivo(tipo, descricao, aderencia, viabilidade));

		return "Cadastro de objetivo realizado com sucesso!";
	}

	public void apagarObjetivo(String codigo) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (objetivosCadastrados.containsKey(codigo)) {
			objetivosCadastrados.remove(codigo);
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}

	public String exibeObjetivo(String codigo) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (objetivosCadastrados.containsKey(codigo)) {
			return codigo + objetivosCadastrados.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

	}
	
	

	public HashMap<String, Objetivo> getObjetivos() {
		return objetivosCadastrados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objetivosCadastrados == null) ? 0 : objetivosCadastrados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControladorObjetivo other = (ControladorObjetivo) obj;
		if (objetivosCadastrados == null) {
			if (other.objetivosCadastrados != null)
				return false;
		} else if (!objetivosCadastrados.equals(other.objetivosCadastrados))
			return false;
		return true;
	}
}