package sistemaDePesquisas;

import java.util.ArrayList;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String status;
	private String codigo;
	private String problema;
	private ArrayList<String> objetivos;

	public Pesquisa(String descricao, String campoDeInteresse,String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativa";
		this.codigo = codigo;
		this.problema = "";
		this.objetivos = new ArrayList<String>();
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

	@Override
	public String toString() {
		return getCodigo() + " - " + getDescricao() + " - " + getCampoDeInteresse();
	}

	

	
}