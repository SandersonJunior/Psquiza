package controladores;

import java.util.HashMap;
import java.util.Map.Entry;

import sistemaDePesquisas.Atividade;
import sistemaDePesquisas.Item;
import validadores.Validador;

public class ControladorAtividade {
	
	private static Validador validador = new Validador();
	private HashMap<String,Atividade> atividadesCriadas = new HashMap<>();
    private HashMap<String, Item> itensAtividade = new HashMap<>();
	
	private int cont = 0;
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		String codigo = "";
		validador.validadorString(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.validadorString(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validadorString(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		if((nivelRisco.equals("BAIXO") || nivelRisco.equals("MEDIO") || nivelRisco.equals("ALTO")) == false) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		else {
			cont += 1;
			codigo = "A" + Integer.toString(cont);
			atividadesCriadas.put(codigo, new Atividade(descricao, nivelRisco, descricaoRisco));	
			return "cadastro bem sucedido!";
		}
	}
	
	public void apagaAtividade(String codigo) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		atividadesCriadas.remove(codigo);
	}
	
	public String exibeAtividade(String codigo) {
		if(atividadesCriadas.containsKey(codigo)) {
			return atividadesCriadas.get(codigo).toString() + strListarAtividades(codigo);
		}else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
	
	public String strListarAtividades(String codigo) { //posicao + "A"
		String listaItens = "";
		for(int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			listaItens += atividadesCriadas.get(codigo).getItensAtividade().get(i).toString();
			}
		return listaItens;
	}
	
	public void cadastraItem(String codigo, String item) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.validadorString(item, "Item nao pode ser nulo ou vazio.");
		if(!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		 atividadesCriadas.get(codigo).getItensAtividade().add(new Item(item, "PENDENTE"));
	}
	
	public int contaItensPendentes(String codigo) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		int cont = 0;
		for(int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			if(atividadesCriadas.get(codigo).getItensAtividade().get(i).getStatus().equals("PENDENTE")){
				cont += 1;
			}
		}
		return cont;
	}
	public int contaItensRealizados(String codigo) {
		validador.validadorString(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(!atividadesCriadas.containsKey(codigo)) {
			throw new NullPointerException("Atividade nao encontrada");
		}
		cont = 0;
		for(int i = 0; i < atividadesCriadas.get(codigo).getItensAtividade().size(); i++) {
			if(atividadesCriadas.get(codigo).getItensAtividade().get(i).getStatus().equals("REALIZADO")){
				cont += 1;
			}
		}
		return cont;		
	}
}
