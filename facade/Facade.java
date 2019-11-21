package facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	private ControladorPesquisador controladorPesquisador = new ControladorPesquisador();

// US1

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
		return controladorP.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return controladorP.pesquisaEhAtiva(codigo);
	}

// US 2

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
		controladorPesquisador.cadastraPesquisador(nome, funcao, biografia, email, foto);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controladorPesquisador.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email) {
		controladorPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		controladorPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return controladorPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return controladorPesquisador.pesquisadorEhAtivo(email);
	}

// US 3

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

// US 4

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

// US 5

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controladorP.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return controladorP.desassociaProblema(idPesquisa, idProblema);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controladorP.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controladorP.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return controladorP.listaPesquisas(ordem);
	}

// US 6
	
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controladorPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double iea) {
		controladorPesquisador.cadastraEspecialidadeAluno(email, semestre, iea);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return controladorP.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return controladorP.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public String listaPesquisadores(String tipo) {
		return controladorPesquisador.listaPesquisadores(tipo);
	}

// US 7
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controladorP.associaAtividade(codigoPesquisa, codigoAtividade);
	}
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return controladorP.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controladorP.executaAtividade(codigoAtividade, item, duracao);
	}
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controlerA.cadastraResultado(codigoAtividade, resultado);
	}
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controlerA.removeResultado(codigoAtividade, numeroResultado);
	}
	public String listaResultados(String codigoAtividade) {
		return controlerA.listaResultados(codigoAtividade); 
	}
	public int getDuracao(String codigoAtividade) {
		return controlerA.getDuracao(codigoAtividade);
	}

// US 8

	public String busca(String termo) {
		return controladorP.busca(termo, controladorP, controladorPesquisador, controlerA, controladorObjetivo,
				controladorProblema);
	}

	public String busca(String termo, int numeroDoResultado) {
		return controladorP.busca(termo, numeroDoResultado, controladorP, controladorPesquisador, controlerA,
				controladorObjetivo, controladorProblema);
	}

	public int contaResultadosBusca(String termo) {
		return controladorP.contaResultadosBusca(termo, controladorP, controladorPesquisador, controlerA,
				controladorObjetivo, controladorProblema);
	}

// US 12

	public void salvar() throws IOException {
		FileOutputStream arquivo = new FileOutputStream("sistemaDePesquisas.Arquivos.ser");
		ObjectOutputStream oos = new ObjectOutputStream(arquivo);
		oos.writeObject(controlerA);
		oos.writeObject(controladorP);
		oos.writeObject(controladorProblema);
		oos.writeObject(controladorObjetivo);
		oos.writeObject(controladorPesquisador);
		oos.close();
	}

	public void carregar() throws IOException, ClassNotFoundException {
		FileInputStream arquivo = new FileInputStream("sistemaDePesquisas.Arquivos.ser");
		ObjectInputStream ois = new ObjectInputStream(arquivo);
		controlerA = (ControladorAtividade) ois.readObject();
		controladorP = (ControladorPesquisa) ois.readObject();
		controladorProblema = (ControladorProblema) ois.readObject();
		controladorObjetivo = (ControladorObjetivo) ois.readObject();
		controladorPesquisador = (ControladorPesquisador) ois.readObject();
		ois.close();
	}

	public static void main(String[] args) {
		args = new String[] { "facade.Facade", "teste_aceitacao/use_case_1.txt", "teste_aceitacao/use_case_2.txt", "teste_aceitacao/use_case_3.txt", "teste_aceitacao/use_case_4.txt", "teste_aceitacao/use_case_5.txt", "teste_aceitacao/use_case_6.txt", "teste_aceitacao/use_case_7.txt", "teste_aceitacao/use_case_8.txt", "teste_aceitacao/use_case_12SALVAR.txt", "teste_aceitacao/use_case_12CARREGAR.txt" };

		EasyAccept.main(args);
	}
}