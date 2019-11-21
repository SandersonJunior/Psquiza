package controladores;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.io.Serializable;
import sistemaDePesquisas.Pesquisa;
import validadores.Validador;

/**
 * Controlador que manipula as ações referentes as pesquisas. Toda a parte de
 * cadastro de uma pesquisa e suas alterações estão nesta classe.
 *
 */

public class ControladorPesquisa implements Serializable {

	/**
	 * validadores de atributos.
	 */
	private Validador validador = new Validador();

	/**
	 * Mapa que guarda as pesquisas no formata <Codigo, Pesquisa>.
	 */
	private HashMap<String, Pesquisa> pesquisas = new LinkedHashMap<>();

	/**
	 * Array que armazena os Objtivos de uma pesquisa.
	 */

	private ArrayList<String> objetivos = new ArrayList<>();
	/*
	 * aponta para o controlador de atividades e seus metodos.
	 */
	private ControladorAtividade controladorAtividade;
	/*
	 * aponta para a classe Pesquisa e seus metodos.
	 */
	private Pesquisa pesquisa;
	private ControladorPesquisador cp;

	/**
	 * Metódo que cadastra uma Pesquisa e lhe guarda no mapa de pesquisas.
	 * 
	 * @param descricao        informações sobre a pesquisa.
	 * @param campoDeInteresse quais interesses estão ligados a essa pesquisa.
	 * @return retorna um codigo único para uma pesquisa criada com sucesso.
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.validaString(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.validaTamanhoCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		validador.validaQuantidadeCampoInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");
		String codigo = campoDeInteresse.substring(0, 3);
		codigo = geraCodigo(codigo, 1);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;
	}

	/**
	 * Metódo auxiliar para gerar o codigo na pesquisa.
	 * 
	 * @param codigo recebe as primeiras 3 letras do campo de interesse.
	 * @param i      um inteiro é o ultimo digito do codigo, caso as 3 primeiras
	 *               letras de codigo forem iguais o digito sempre sera diferente.
	 * @return
	 */
	private String geraCodigo(String codigo, int i) {
		String cod = codigo + i;
		cod = cod.toUpperCase();
		while (pesquisas.containsKey(cod)) {
			i++;
			cod = codigo + i;
		}
		return cod.toUpperCase();
	}

	/**
	 * Metodo que pega o Mapa de pesquisas.
	 * 
	 * @return retorna o mapa de pesquisas.
	 */
	public HashMap<String, Pesquisa> getPesquisas() {
		return pesquisas;
	}

	/**
	 * Altera a Descricao ou o Campo de uma pesquisa, lhe atribuindo um novo valor.
	 * 
	 * @param codigo               codigo da pesquisa que vai ser alterada.
	 * @param conteudoASerAlterado Se vai ser o Campo ou a Descricao.
	 * @param novoConteudo         Novo conteudo que vai ser adcionado.
	 */

	public boolean existePesquisa(String codigo) {
		boolean existe = false;

		if (pesquisas.containsKey(codigo)) {
			existe = true;
		}

		return existe;
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (!existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (!conteudoASerAlterado.equals("CAMPO") && !conteudoASerAlterado.equals("DESCRICAO")) {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
		if (pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("CAMPO")) {
			validador.validaTamanhoCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			validador.validaQuantidadeCampoInteresse(novoConteudo, "Formato do campo de interesse invalido.");
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			validador.validaString(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		}
	}

	/**
	 * Encerra o status da pesquisa como "desativado".
	 * 
	 * @param codigo codigo da pesquisa que vai ser alterada.
	 * @param motivo descricao do motivo do encerramento da pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		if (!existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		validador.validaString(motivo, "Motivo nao pode ser nulo ou vazio.");
		if (pesquisas.get(codigo).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		pesquisas.get(codigo).setStatus("desativada");
	}

	/**
	 * Ativa uma pesquisa que esta com status "desativado".
	 * 
	 * @param codigo codigo da pesquisa que vai ser alterada.
	 */
	public void ativaPesquisa(String codigo) {
		if (!existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(codigo).getStatus().equals("ativa")) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
		pesquisas.get(codigo).setStatus("ativa");
	}

	/**
	 * Exibe uma pesquisa no seguinte formato (Codigo - descricao - campo de
	 * interesse)
	 * 
	 * @param codigo codigo da pesquisa que vai ser aexibida.
	 * @return retorna a String da pesquisa.
	 */
	public String exibePesquisa(String codigo) {

		if (!existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		return pesquisas.get(codigo).toString();
	}

	/**
	 * Verifica se uma pesquisa tem Status ativo ou não.
	 * 
	 * @@param codigo codigo da pesquisa que vai ser verificada.
	 * @return retorna true caso a pesquisa seja ativa ou false caso contrário.
	 */

	public boolean pesquisaEhAtiva(String codigo) {
		validador.validaString(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (!existePesquisa(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (pesquisas.get(codigo).getStatus().equals("ativa")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (!existePesquisa(idPesquisa)) {
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
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (!existePesquisa(idPesquisa)) {
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
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!existePesquisa(idPesquisa)) {
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
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

		if (!existePesquisa(idPesquisa)) {
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
		validador.validaTipoPesquisa(ordem, "Valor invalido da ordem");
		ArrayList<String> maiorValor = new ArrayList<>();
		for (String key : pesquisas.keySet()) {
			maiorValor.add(key);
		}
		String lista = "";
		if (ordem.equals("PROBLEMA")) {
			for (int i = (maiorValor.size()) - 1; i > -1; i--) {
				if (!pesquisas.get(maiorValor.get(i)).getProblema().equals("")) {
					lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
				}
			}
			for (int i = (maiorValor.size()) - 1; i > -1; i--) {
				if (pesquisas.get(maiorValor.get(i)).getProblema().equals("")) {
					lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
				}

			}

		} else if (ordem.equals("OBJETIVOS")) {
			String aux = "";
			for (int i = 0; i < (maiorValor.size()); i++) {
				for (int j = 0; j < (maiorValor.size()); j++) {
					if (pesquisas.get(maiorValor.get(i)).getObjetivos().size() < pesquisas.get(maiorValor.get(j))
							.getObjetivos().size()) {
						aux = maiorValor.get(i);
						maiorValor.set(i, maiorValor.get(j));
						maiorValor.set(j, aux);
					}
				}

			}
			for (int i = (maiorValor.size()) - 1; i > -1; i--) {
				lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
			}
		} else if (ordem.equals("PESQUISA")) {
			String aux = "";
			for (int i = 0; i < (maiorValor.size()); i++) {
				for (int j = 0; j < (maiorValor.size()); j++) {
					if (pesquisas.get(maiorValor.get(i)).getObjetivos().size() > pesquisas.get(maiorValor.get(j))
							.getObjetivos().size()) {
						aux = maiorValor.get(i);
						maiorValor.set(i, maiorValor.get(j));
						maiorValor.set(j, aux);
					}
				}

			}
			for (int i = (maiorValor.size()) - 1; i > -1; i--) {
				lista += pesquisas.get(maiorValor.get(i)).toString() + " | ";
			}
		}

		return lista.substring(0, lista.length() - 3);

	}

	public String busca(String termo, ControladorPesquisa CP, ControladorPesquisador Cpdor, ControladorAtividade CA,
			ControladorObjetivo CO, ControladorProblema Cpro) {
		validador.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		ArrayList<String> lista = new ArrayList<>();
		String palavra = "";
		for (String key : CP.getPesquisas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CP.getPesquisas().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += CP.getPesquisas().get(lista.get(i)).getCodigo() + ": "
						+ CP.getPesquisas().get(lista.get(i)).getDescricao() + " | ";
			}
			if (CP.getPesquisas().get(lista.get(i)).getCampoDeInteresse().contains(termo)) {
				palavra += CP.getPesquisas().get(lista.get(i)).getCodigo() + ": "
						+ CP.getPesquisas().get(lista.get(i)).getCampoDeInteresse() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : Cpdor.getPesquisadores().keySet()) {
			lista.add(key);
		}
		for (int j = (lista.size() - 1); j > -1; j--) {
			if (Cpdor.getPesquisadores().get(lista.get(j)).getBiografia().contains(termo)) {
				palavra += Cpdor.getPesquisadores().get(lista.get(j)).getEmail() + ": "
						+ Cpdor.getPesquisadores().get(lista.get(j)).getBiografia() + " | ";
			}
		}
		lista = new ArrayList<>();
		for (String key : Cpro.getProblemas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (Cpro.getProblemas().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + Cpro.getProblemas().get(lista.get(i)).getDescricao() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : CO.getObjetivos().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CO.getObjetivos().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + CO.getObjetivos().get(lista.get(i)).getDescricao() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : CA.getAtividades().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CA.getAtividades().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + CA.getAtividades().get(lista.get(i)).getDescricao() + " | ";
			}
			if (CA.getAtividades().get(lista.get(i)).getDescricaoRisco().contains(termo)) {
				palavra += lista.get(i) + " : " + CA.getAtividades().get(lista.get(i)).getDescricaoRisco() + " | ";
			}
		}
		if (palavra.length() == 0) {
			return palavra;
		}
		return palavra.substring(0, palavra.length() - 3);
	}

	public String busca(String termo, int numeroDoResultado, ControladorPesquisa CP, ControladorPesquisador Cpdor,
			ControladorAtividade CA, ControladorObjetivo CO, ControladorProblema Cpro) {
		validador.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		validador.validaInteiro(numeroDoResultado, "Numero do resultado nao pode ser negativo");
		ArrayList<String> lista = new ArrayList<>();
		String[] busca;
		String palavra = "";
		for (String key : CP.getPesquisas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CP.getPesquisas().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += CP.getPesquisas().get(lista.get(i)).getCodigo() + ": "
						+ CP.getPesquisas().get(lista.get(i)).getDescricao() + " | ";
			}
			if (CP.getPesquisas().get(lista.get(i)).getCampoDeInteresse().contains(termo)) {
				palavra += CP.getPesquisas().get(lista.get(i)).getCodigo() + ": "
						+ CP.getPesquisas().get(lista.get(i)).getCampoDeInteresse() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : Cpdor.getPesquisadores().keySet()) {
			lista.add(key);
		}
		for (int j = (lista.size() - 1); j > -1; j--) {
			if (Cpdor.getPesquisadores().get(lista.get(j)).getBiografia().contains(termo)) {
				palavra += Cpdor.getPesquisadores().get(lista.get(j)).getEmail() + ": "
						+ Cpdor.getPesquisadores().get(lista.get(j)).getBiografia() + " | ";
			}
		}
		lista = new ArrayList<>();
		for (String key : Cpro.getProblemas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (Cpro.getProblemas().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + Cpro.getProblemas().get(lista.get(i)).getDescricao() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : CO.getObjetivos().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CO.getObjetivos().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + CO.getObjetivos().get(lista.get(i)).getDescricao() + " | ";
			}
		}

		lista = new ArrayList<>();
		for (String key : CA.getAtividades().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CA.getAtividades().get(lista.get(i)).getDescricao().contains(termo)) {
				palavra += lista.get(i) + ": " + CA.getAtividades().get(lista.get(i)).getDescricao() + " | ";
			}
			if (CA.getAtividades().get(lista.get(i)).getDescricaoRisco().contains(termo)) {
				palavra += lista.get(i) + " : " + CA.getAtividades().get(lista.get(i)).getDescricaoRisco() + " | ";
			}
		}
		busca = palavra.split(" " + "\\|" + " ");
		if (numeroDoResultado - 1 > busca.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return busca[numeroDoResultado - 1];
	}

	public int contaResultadosBusca(String termo, ControladorPesquisa CP, ControladorPesquisador Cpdor,
			ControladorAtividade CA, ControladorObjetivo CO, ControladorProblema Cpro) {
		validador.validaString(termo, "Campo termo nao pode ser nulo ou vazio.");
		ArrayList<String> lista = new ArrayList<>();
		int cont = 0;
		for (String key : CP.getPesquisas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CP.getPesquisas().get(lista.get(i)).getDescricao().contains(termo)) {
				cont++;
			}
			if (CP.getPesquisas().get(lista.get(i)).getCampoDeInteresse().contains(termo)) {
				cont++;
			}
		}

		lista = new ArrayList<>();
		for (String key : Cpdor.getPesquisadores().keySet()) {
			lista.add(key);
		}
		for (int j = (lista.size() - 1); j > -1; j--) {
			if (Cpdor.getPesquisadores().get(lista.get(j)).getBiografia().contains(termo)) {
				cont++;
			}
		}
		lista = new ArrayList<>();
		for (String key : Cpro.getProblemas().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (Cpro.getProblemas().get(lista.get(i)).getDescricao().contains(termo)) {
				cont++;
			}
		}

		lista = new ArrayList<>();
		for (String key : CO.getObjetivos().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CO.getObjetivos().get(lista.get(i)).getDescricao().contains(termo)) {
				cont++;
			}
		}

		lista = new ArrayList<>();
		for (String key : CA.getAtividades().keySet()) {
			lista.add(key);
		}
		for (int i = (lista.size()) - 1; i > -1; i--) {
			if (CA.getAtividades().get(lista.get(i)).getDescricao().contains(termo)) {
				cont++;
			}
			if (CA.getAtividades().get(lista.get(i)).getDescricaoRisco().contains(termo)) {
				cont++;
			}
		}
		if (cont == 0)
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		return cont;
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		if (!existePesquisa(idPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (pesquisas.get(idPesquisa).getPesquisador().equals("")) {
			pesquisas.get(idPesquisa).setPesquisador(emailPesquisador);
			return true;
		}

		return false;
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validaString(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validaString(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		if (!existePesquisa(idPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

		if (pesquisas.get(idPesquisa).getStatus().equals("desativada")) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (!pesquisas.get(idPesquisa).getPesquisador().equals("")) {
			pesquisas.get(idPesquisa).setPesquisador("");
			return true;
		}

		return false;
	}
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		boolean retorno = false;
		
		if(pesquisas.containsKey(codigoPesquisa)) { 
			if(controladorAtividade.getAtividades().containsKey(codigoAtividade)) {
				if(pesquisas.get(codigoPesquisa).getStatus().equals("ativa")) {
					pesquisas.get(codigoPesquisa).getAtividadesAssociadas().add(controladorAtividade.getAtividades().get(codigoAtividade));
					if(pesquisas.get(codigoPesquisa).getAtividade().equals("")) {
						pesquisas.get(codigoPesquisa).setAtividade(codigoAtividade);
						retorno = true;
					}
				}else{
					throw new IllegalArgumentException("Pesquisa desativada.");
				}
			}else { 
				throw new IllegalArgumentException("Atividade nao encontrada");
			}
		}else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		return retorno;
	}
	
	
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validaString(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		boolean retorno = false;
		int posicao = Integer.parseInt(codigoAtividade.substring(1, codigoAtividade.length()));
		
		if(!pesquisas.containsKey(codigoPesquisa)) { 
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if(!pesquisas.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}else {
			pesquisas.get(codigoPesquisa).getAtividadesAssociadas().remove(posicao);
			retorno = true;
		}
		return retorno;
	}
	
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.validaString(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validaDuracao(duracao);
		validador.validaItem(item);
		
		int posicao = Integer.parseInt(codigoAtividade.substring(1, codigoAtividade.length()));
		
		if(pesquisa.getAtividadesAssociadas().get(posicao).equals(null)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}else {
			if(pesquisa.getAtividadesAssociadas().get(posicao).getItensAtividade().get(item).equals(null)) {
				throw new IllegalArgumentException("Item nao encontrado.");
			}else {
				if(pesquisa.getAtividadesAssociadas().get(posicao).getItensAtividade().get(item).getStatus().equals("REALIZADO")){
					throw new IllegalArgumentException("Item ja executado.");
				}else{
					pesquisa.getAtividadesAssociadas().get(posicao).getItensAtividade().get(item).setStatus("REALIZADO");
				}
			}
		}
	}

}