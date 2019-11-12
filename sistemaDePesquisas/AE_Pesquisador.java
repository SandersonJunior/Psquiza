package sistemaDePesquisas;

import controladores.ControladorPesquisador;

public class AE_Pesquisador {

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data, ControladorPesquisador controller) {
		if (controller.existePesquisador(email)) {

		}
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {

	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {

		return true;
	}
}
