package facade;

import controladores.ControladorPesquisa;

import easyaccept.EasyAccept;

public class Facade {
	
	ControladorPesquisa controladorP = new ControladorPesquisa();

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controladorP.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		ControladorPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		ControladorPesquisa.encerraPesquisa(codigo, motivo);
	}
	
	public void ativaPesquisa(String codigo) {
		ControladorPesquisa.ativaPesquisa(codigo);
	}
	
	public String exibePesquisa(String codigo) {
		return ControladorPesquisa.exibePesquisa(codigo);
	}
	
	public boolean pesquisaEhAtiva(String codigo) {
		return ControladorPesquisa.pesquisaEhAtiva(codigo);
	}
	
	public static void main(String[] args) {
        args = new String[] {
                "facade.Facade",
               "teste_aceitacao/use_case_1.txt"//, "teste_aceitacao/use_case_2.txt",
//                "teste_aceitacao/use_case_3.txt", "teste_aceitacao/use_case_4.txt"
                };

        EasyAccept.main(args);
	}
}
