package facade;

import controladores.ControladorPesquisa;


import easyaccept.EasyAccept;
import sistemaDePesquisas.Atividade;
import controladores.ControladorAtividade;;

public class Facade {
	private ControladorAtividade controlerA = new ControladorAtividade();
	private ControladorPesquisa controladorP = new ControladorPesquisa();

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controladorP.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controladorP.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		controladorP.encerraPesquisa(codigo, motivo);
	}
	
	public void ativaPesquisa(String codigo) {
		controladorP.ativaPesquisa(codigo);
	}
	
	public String exibePesquisa(String codigo) {
		return ControladorPesquisa.exibePesquisa(codigo);
	}
	
	public boolean pesquisaEhAtiva(String codigo) {
		return ControladorPesquisa.pesquisaEhAtiva(codigo);
	}	
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return controlerA.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}
	public void apagaAtividade(String codigo) {
		controlerA.apagaAtividade(codigo);
	}
	public void cadastraItem(String codigo, String item) {
		controlerA.cadastraItem(codigo, item);
	}
	public String exibeAtividade(String codigo) {
		return controlerA.exibeAtividade(codigo);
	}
	public int contaItensPendentes(String codigo) {
		return controlerA.contaItensPendentes(codigo);
	}
	public int contaItensRealizados(String codigo) {
		return controlerA.contaItensRealizados(codigo);
	}
	
	public static void main(String[] args) {
        args = new String[] {
                "facade.Facade",
               "teste_aceitacao/use_case_1.txt"//, "teste_aceitacao/use_case_2.txt",
//                "teste_aceitacao/use_case_3.txt", 
                ,"teste_aceitacao/use_case_4.txt"
                };

        EasyAccept.main(args);
	}
}
