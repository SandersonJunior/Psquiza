package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controladores.ControladorPesquisador;

class PesquisadorTest {
	
	ControladorPesquisador controlePesquisador;

	@BeforeEach
	void setUp() {
		this.controlePesquisador = new ControladorPesquisador();
		controlePesquisador.cadastraPesquisador("Gutto", "aluno", "desenvolvedor nas horas vagas", "gugu@hotloiro.com", "https://gugugod");
	}
	
	@Test
	void testCadastraPesquisador() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("", "professor", "ajudante de pedreiro", "tonho@yakut.com", "https://cacau");});
		assertEquals("Campo nome nao pode ser nulo ou vazio.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "", "ajudante de pedreiro", "tonho@yakut.com", "https://cacau");});
		assertEquals("Campo funcao nao pode ser nulo ou vazio.", exception2.getMessage());
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "professor", "", "tonho@yakut.com", "https://cacau");});
		assertEquals("Campo biografia nao pode ser nulo ou vazio.", exception3.getMessage());
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "professor", "ajudante de pedreiro", "", "https://cacau");});
		assertEquals("Campo email nao pode ser nulo ou vazio.", exception4.getMessage());
		IllegalArgumentException exception5 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "professor", "ajudante de pedreiro", "tonhoyakut.com", "https://cacau");});
		assertEquals("Formato de email invalido.", exception5.getMessage());
		IllegalArgumentException exception6 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "professor", "ajudante de pedreiro", "tonho@yakut.com", "");});
		assertEquals("Campo fotoURL nao pode ser nulo ou vazio.", exception6.getMessage());
		IllegalArgumentException exception7 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.cadastraPesquisador("Gulert", "professor", "ajudante de pedreiro", "tonho@yakut.com", "https");});
		assertEquals("Formato de foto invalido.", exception7.getMessage());
	}
	
	@Test
	void testAlteraPesquisador() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("","NOME", "Caio");});
		assertEquals("Campo email nao pode ser nulo ou vazio.", exception.getMessage());
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugugod", "NOME", "Caio");});
		assertEquals("Formato de email invalido.", exception2.getMessage());
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugugod@yakut.com", "NOME", "Caio");});
		assertEquals("Pesquisador nao encontrado", exception3.getMessage());
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugu@hotloiro.com", "nOmE", "Caio");});
		assertEquals("Atributo invalido.", exception4.getMessage());
		IllegalArgumentException exception5 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugu@hotloiro.com", "NOME", "");});
		assertEquals("Campo nome nao pode ser nulo ou vazio.", exception5.getMessage());
		IllegalArgumentException exception6 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugu@hotloiro.com", "FUNCAO", "");});
		assertEquals("Campo funcao nao pode ser nulo ou vazio.", exception6.getMessage());
		IllegalArgumentException exception7 = assertThrows(IllegalArgumentException.class, () -> {controlePesquisador.alteraPesquisador("gugu@hotloiro.com", "BIOGRAFIA", "");});
		assertEquals("Campo biografia nao pode ser nulo ou vazio.", exception7.getMessage());
	}
}