package sistemaDePesquisas;

import interfaces.Especialidade;

public class Aluno extends Pesquisador implements Especialidade {
	private int semestre;
	private double iea;

	public Aluno(String nome, String funcao, String biografia, String email, String foto, int semestre, double iea) {
		super(nome, funcao, biografia, email, foto);
		this.semestre = semestre;
		this.iea = iea;
	}

	public int getSemestre() {
		return semestre;
	}

	public double getIea() {
		return iea;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + getSemestre() + "o SEMESTRE - " + getIea();
	}

}
