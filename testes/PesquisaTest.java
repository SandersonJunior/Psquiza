package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controladores.ControladorPesquisa;

class PesquisaTest {

	private ControladorPesquisa controleP;

	@BeforeEach
	void setUp() {
		this.controleP = new ControladorPesquisa();
		controleP.cadastraPesquisa("Tentativa de fazer esse projeto funcionar.", "estudo, UFCG");
	}
	
	@Test
	void testCadastraPesquisa() {
		assertEquals("PRO1", controleP.cadastraPesquisa("Se manter acordado.", "projeto, UFCG"));
		assertThrows(IllegalArgumentException.class, () -> controleP.cadastraPesquisa("", "estudo, UFCG"), "Descricao nao pode ser nula ou vazia.");
		assertThrows(IllegalArgumentException.class, () -> controleP.cadastraPesquisa("Tentativa de fazer esse projeto funcionar.", ""), "Formato do campo de interesse invalido.");
	}
	
	@Test
	void testAlteraPesquisa() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controleP.alteraPesquisa("", "CAMPO", "jogos");});
		assertEquals("Pesquisa nao encontrada.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,  () -> controleP.alteraPesquisa("EST1", "", "jogos"));
		assertEquals("Nao e possivel alterar esse valor de pesquisa.", exception2.getMessage());
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,  () -> controleP.alteraPesquisa("EST1", "CAMPO", ""));
		assertEquals("Formato do campo de interesse invalido.", exception3.getMessage());
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class,  () -> controleP.alteraPesquisa("EST1", "DESCRICAO", ""));
		assertEquals("Descricao nao pode ser nula ou vazia.", exception4.getMessage());

	}
	
	@Test
	void testAtivaPesquisa() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controleP.ativaPesquisa("EST1");});
		assertEquals("Pesquisa ja ativada.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {controleP.ativaPesquisa("EST2");});
		assertEquals("Pesquisa nao encontrada.", exception2.getMessage());
	}
	
	@Test
	void testEncerraPesquisa() {
		controleP.encerraPesquisa("EST1", "conseguimos");
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controleP.encerraPesquisa("EST1", "Conseguimos");});
		assertEquals("Pesquisa desativada.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {controleP.encerraPesquisa("EST2", "Ja deu bom");});
		assertEquals("Pesquisa nao encontrada.", exception2.getMessage());
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> {controleP.encerraPesquisa("EST1", "");});
		assertEquals("Motivo nao pode ser nulo ou vazio.", exception3.getMessage());
	}
	
	@Test
	void testExibePesquisa() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controleP.exibePesquisa("EST2");});
		assertEquals("Pesquisa nao encontrada.", exception.getMessage());
		assertEquals("EST1 - Tentativa de fazer esse projeto funcionar. - estudo, UFCG", controleP.exibePesquisa("EST1"));
	}
	
	@Test
	void testPesquisaEhAtiva() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controleP.pesquisaEhAtiva("EST2");});
		assertEquals("Pesquisa nao encontrada.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {controleP.pesquisaEhAtiva("");});
		assertEquals("Codigo nao pode ser nulo ou vazio.", exception2.getMessage());
		assertTrue(controleP.pesquisaEhAtiva("EST1"));
	}

}
