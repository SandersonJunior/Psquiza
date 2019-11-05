package sistemaDePesquisas;

public class Aluno extends Pesquisador {
	private String semestre;
	private double iea;

	public Aluno(String nome, String biografia, String email, String foto, String funcao, String semestre, double iea) {
		super(nome, biografia, email, foto, funcao);
		this.semestre = semestre;
		this.iea = iea;
	}

	public String getSemestre() {
		return semestre;
	}

	public double getIea() {
		return iea;
	}

	@Override
	public String toString() {
		return super.toString() + " - " + getSemestre() + " - " + getIea();
	}

}
