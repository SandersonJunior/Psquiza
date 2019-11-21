package sistemaDePesquisas;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * O objeto pesquisa � criado seguindo as especifica��es desta classe. Aqui temos seus atributos.
 *
 */

public class Pesquisa implements Serializable {
	/**
	 * variavel que guarda a descricao de uma pesquisa.
	 */
	private String descricao;
	/**
	 * variavel que guarda o campo de interesse de uma pesquisa.
	 */
	private String campoDeInteresse;
	/**
	 * variavel que guarda o status de uma pesquisa.
	 */
	private String status;
	/**
	 * variavel que guarda o c�digo �nico de uma pesquisa.
	 */
	private String codigo;
	/**
	 * variavel que guarda o problema ao qual a pesquisa se atrela.
	 */
	private String problema;
	/**
	 * Array que guarda os objetivos da pesquisa.
	 */
	private ArrayList<String> objetivos;
	/*
	 * Array que guarda atividades da pesquisa.
	 */
	private ArrayList<Atividade> atividades;
	
	/*
	 * string que guarda identificador de atividade.
	 */
	private String atividade;
	/**
	 * Construtor de uma pesquisa.
	 * @param descricao descricao de uma pesquisa.
	 * @param campoDeInteresse campo de interesse de uma pesquisa.
	 * @param codigo codigo da pesquisa.
	 */
	
	private String pesquisador;
	
	public Pesquisa(String descricao, String campoDeInteresse,String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativa";
		this.codigo = codigo;
		this.problema = "";
		this.pesquisador = "";
		this.objetivos = new ArrayList<String>();
		this.atividades = new ArrayList<Atividade>();
		this.atividade = "";
		}

	public String getCodigo() {
		return codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}
	
	public void setProblema(String idProblema) {
		this.problema = idProblema;
	}
	
	public String getProblema() {
		return problema;
	}
	
	public ArrayList<String> getObjetivos() {
		return objetivos;
	}
	
	public ArrayList<Atividade> getAtividadesAssociadas() {
		return atividades;
	}

	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(String pesquisador) {
		this.pesquisador = pesquisador;
	}

	/**
	 * retorna em formato de string uma pesquisa.
	 */
	@Override
	public String toString() {
		return getCodigo() + " - " + getDescricao() + " - " + getCampoDeInteresse();
	}


	

	
}