package controladores;

import java.util.HashMap;

import java.io.Serializable;
import sistemaDePesquisas.Atividade;
import sistemaDePesquisas.Item;
import validadores.Validador;
import java.io.Serializable;
import sistemaDePesquisas.Pesquisa;
import sistemaDePesquisas.Resultado;

public class ControladorAtividade implements Serializable {

	private static Validador validador = new Validador();
	private HashMap<String, Atividade> atividadesCriadas = new HashMap<>();
	private Pesquisa pesquisa;
	private int cont = 0;

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		String codigo = "";
		validador.validaString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.validaString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validaString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		if ((nivelRisco.equals("BAIXO") || nivelRisco.equals("MEDIO") || nivelRisco.equals("ALTO")) == false) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		
		this.cont += 1;
		codigo = "A" + Integer.toString(cont);
		atividadesCriadas.put(codigo, new Atividade(descricao, nivelRisco, descricaoRisco));
		return "cadastro bem sucedido!";

	}

	public void apagaAtividade(String codigo) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		atividadesCriadas.remove(codigo);
	}

	public String exibeAtividade(String codigo) {
		if (atividadesCriadas.containsKey(codigo)) {
			return atividadesCriadas.get(codigo).toString() + strListarAtividades(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	public String strListarAtividades(String codigo) { // posicao + "A"
		String listaItens = "";
		for (int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			listaItens += atividadesCriadas.get(codigo).getItensAtividade().get(i).toString();
		}
		return listaItens;
	}

	public void cadastraItem(String codigo, String item) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.validaString(item, "Item nao pode ser nulo ou vazio.");
		if (!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		atividadesCriadas.get(codigo).getItensAtividade().add(new Item(item, "PENDENTE"));
	}

	public int contaItensPendentes(String codigo) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		int cont = 0;
		for (int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			if (atividadesCriadas.get(codigo).getItensAtividade().get(i).getStatus().equals("PENDENTE")) {
				cont += 1;
			}
		}
		return cont;
	}

	public int contaItensRealizados(String codigo) {
		validador.validaString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		int contador = 0;
		for (int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			if (atividadesCriadas.get(codigo).getItensAtividade().get(i).getStatus().equals("REALIZADO")) {
				contador += 1;
			}
		}
		return contador;
	}

	public HashMap<String, Atividade> getAtividades() {
		return atividadesCriadas;
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validaString(codigoAtividade, "Resultado nao pode ser nulo ou vazio.");
		
		if(atividadesCriadas.containsKey(codigoAtividade)) {
			atividadesCriadas.get(codigoAtividade).getResultados().add(new Resultado(resultado));				
		}
		if(atividadesCriadas.get(codigoAtividade).getResultados().size() == 0) {
			throw new IllegalArgumentException("Resultado nao pode ser nulo ou vazio.");
		}else {
			return atividadesCriadas.get(codigoAtividade).getResultados().size();			
		}
	}
	
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validaNumResultado(numeroResultado);
		
		int checagem1 = 0;
		int checagem2 = 0;
		boolean retorno = false;
		
		for (int i = 0; i < pesquisa.getAtividadesAssociadas().size(); i++) {	
			checagem1 += 1;
			if(atividadesCriadas.containsKey(codigoAtividade)) {
				for(int j = 0; j < atividadesCriadas.get(codigoAtividade).getResultados().size(); j++) {
					if(atividadesCriadas.get(codigoAtividade).getResultados().get(j).equals(numeroResultado)) {
						atividadesCriadas.get(codigoAtividade).getResultados().remove(j);
						checagem2 += 1;
						retorno = true;
					}else if(j == atividadesCriadas.get(codigoAtividade).getResultados().size() && checagem2 == 0) {
						retorno = false;
						throw new IllegalArgumentException("Resultado nao encontrado.");
					}
				}
			}else if(i == atividadesCriadas.size() && checagem1 == 0) {
				retorno = false;
				throw new IllegalArgumentException("Atividade nao encontrada");
			}
			
		}
		return retorno;
	}
			
	public String listaResultados(String codigoAtividade) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		String listar = "";
		
		if(atividadesCriadas.containsKey(codigoAtividade)) {
			for (Resultado result : atividadesCriadas.get(codigoAtividade).getResultados()) {
				listar += result.toString() + " | ";
			}
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");	
		}
		return listar.substring(0, listar.length()-3);
	}

	public int getDuracao(String codigoAtividade) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if(atividadesCriadas.containsKey(codigoAtividade)) {
			return atividadesCriadas.get(codigoAtividade).getDuracao();
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		
	}
}