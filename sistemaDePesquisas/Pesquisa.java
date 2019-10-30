package sistemaDePesquisas;

import java.util.HashMap;

import javax.swing.table.TableStringConverter;

public class Pesquisa {
	private String descricao;
	private String campoDeInteresse;
	private String status;
	private String codigo;

	public Pesquisa(String descricao, String campoDeInteresse,String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.status = "ativa";
		this.codigo = codigo;
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

	@Override
	public String toString() {
		return getCodigo() + " - " + getDescricao() + " - " + getCampoDeInteresse();
	}

	
}
