package facade;

import controladores.ControladorAtividade;
import controladores.ControladorPesquisa;
import controladores.ControladorPesquisador;
import controladores.ControladorProblema;
import controladores.ControladorObjetivo;

import easyaccept.EasyAccept;

public class Facade {
	private ControladorAtividade controlerA = new ControladorAtividade();
	private ControladorPesquisa controladorP = new ControladorPesquisa();
	private ControladorProblema controladorProblema = new ControladorProblema();
	private ControladorObjetivo controladorObjetivo = new ControladorObjetivo();

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
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto){
		ControladorPesquisador.cadastraPesquisador(nome, funcao, biografia, email, foto);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor){
		ControladorPesquisador.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email){
		ControladorPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email){
		ControladorPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email){
		return ControladorPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email){
		return ControladorPesquisador.pesquisadorEhAtivo(email);
	}
	
	public String cadastraProblema(String descricao, int viabilidade) {
		return controladorProblema.cadastraProblema(descricao, viabilidade);
	}
	
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return controladorObjetivo.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}
	
	public void apagarProblema(String codigo) {
		controladorProblema.apagarProblema(codigo);
	}
	
	public void apagarObjetivo(String codigo) {
		controladorObjetivo.apagarObjetivo(codigo);
	}
	
	public String exibeProblema(String codigo) {
		return controladorProblema.exibeProblema(codigo);
	}
	
	public String exibeObjetivo(String codigo) {
		return controladorObjetivo.exibeObjetivo(codigo);
	}

	public static void main(String[] args) {
        args = new String[] {
                "facade.Facade",
               "teste_aceitacao/use_case_1.txt", "teste_aceitacao/use_case_2.txt",
                "teste_aceitacao/use_case_3.txt", 
                "teste_aceitacao/use_case_4.txt"
                };

        EasyAccept.main(args);
	}
}