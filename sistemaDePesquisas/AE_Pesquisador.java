package sistemaDePesquisas;

import controladores.ControladorPesquisador;

public class AE_Pesquisador {

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		if (ControladorPesquisador.existePesquisador(email)) {
			
		}
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {

	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {

		return true;
	}
}
