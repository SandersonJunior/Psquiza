package controladores;

import java.util.HashMap;

import sistemaDePesquisas.Problema;
import validadores.Validador;

public class ControladorProblema {
	private static Validador validador = new Validador();
	private HashMap<String, Problema> problemasCadastrados = new HashMap<>();
	
	int cont = 0;
	public String cadastraProblema(String descricao, int viabilidade) {
		validador.validadorString(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validaViabilidade(viabilidade, "Valor invalido de viabilidade.");
		
		cont += 1;
		problemasCadastrados.put("P" + Integer.toString(cont), new Problema(descricao, viabilidade));				
	
		return "Cadastro de problema realizado com sucesso!";
	}
	
	public void apagarProblema(String codigo) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if(problemasCadastrados.containsKey(codigo)) {	
			problemasCadastrados.remove(codigo);
		}else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}
	
	public String exibeProblema(String codigo) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(problemasCadastrados.containsKey(codigo)) {
			return codigo + problemasCadastrados.get(codigo).toString();			
		}else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((problemasCadastrados == null) ? 0 : problemasCadastrados.hashCode());
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
		ControladorProblema other = (ControladorProblema) obj;
		if (problemasCadastrados == null) {
			if (other.problemasCadastrados != null)
				return false;
		} else if (!problemasCadastrados.equals(other.problemasCadastrados))
			return false;
		return true;
	}
	
	
}
