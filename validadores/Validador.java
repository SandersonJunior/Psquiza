package validadores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Validador {

	public void validaString(String palavra, String mensagem) {
		if (palavra == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(palavra)) {
			throw new IllegalArgumentException(mensagem);
		}

	}

	public void validaInt(Integer numero, String mensagem) {
		if (numero == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(numero.toString())) {
			throw new IllegalArgumentException(mensagem);

		}
	}

	public void validaTipoObjetivo(String palavra, String mensagem) {
		if (!palavra.equals("GERAL") && !palavra.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validaTipoPesquisa(String palavra, String mensagem) {
		if (!palavra.equals("PROBLEMA") && !palavra.equals("OBJETIVOS") && !palavra.equals("PESQUISA")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validaTamanhoCampoInteresse(String campoInteresse, String mensagem) {
		if (campoInteresse.length() > 255 || campoInteresse.length() < 3) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validaQuantidadeCampoInteresse(String campoInteresse, String mensagem) {
		String[] lista = campoInteresse.split(",");
		if (lista.length > 4)
			throw new IllegalArgumentException(mensagem);
		else {
			for (int i = 0; i < lista.length; i++) {
				if ("".equals(lista[i].trim()) || lista[i].equals(" ")) {
					throw new IllegalArgumentException(mensagem);
				}
			}
		}
	}

	private boolean validaDigitoOuLetra(String nome) {
		for (char letra : nome.toCharArray()) {
			if (Character.isAlphabetic(letra) || Character.isDigit(letra))
				return true;
		}
		return false;
	}

	public void validaEmail(String email) {
		validaString(email, "Campo email nao pode ser nulo ou vazio.");

		if (!email.contains("@"))
			throw new IllegalArgumentException("Formato de email invalido.");

		int indexArroba = email.indexOf("@");

		if (indexArroba == 0 || indexArroba == email.length() - 1)
			throw new IllegalArgumentException("Formato de email invalido.");

		String antes = email.substring(0, indexArroba);
		String depois = email.substring(indexArroba + 1, email.length());

		if (!validaDigitoOuLetra(antes) || !validaDigitoOuLetra(depois))
			throw new IllegalArgumentException("Formato de email invalido.");

	}

	public void validaFoto(String foto) {
		validaString(foto, "Campo fotoURL nao pode ser nulo ou vazio.");

		if (foto.length() < 7) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
		if (foto.substring(0, 7).equals("http://") || foto.substring(0, 8).equals("https://")) {
			int index = foto.substring(0, 7).equals("http://") ? 7 : 8;
			if (!validaDigitoOuLetra(foto.substring(index, foto.length()))) {
				throw new IllegalArgumentException("Formato de foto invalido.");
			}
		}

		else {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	public void validaViabilidade(int valor, String mensagem) {
		validaInt(valor, "Campo viabilidade nao pode ser nulo ou vazio.");

		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validaAderencia(int valor, String mensagem) {
		validaInt(valor, "Campo aderencia nao pode ser nulo ou vazio.");

		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public boolean validaData(String data) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);

		if (data.length() != 10) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}

		try {
			dateFormat.parse(data.trim());
		} catch (Exception e) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
		
		return true;
	}
	
	public void validaProfessor(String email, String formacao, String unidade, String data) {
		validaString(email, "Campo email nao pode ser nulo ou vazio.");
		validaString(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validaString(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validaString(data, "Campo data nao pode ser nulo ou vazio.");
		validaData(data);
	}
	
	public void validaAluno(String email, int semestre, double iea) {
		validaString(email, "Campo email nao pode ser nulo ou vazio.");
		
		if (semestre < 1) {
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		}
		
		if (iea < 0 || iea > 10) {
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		}
	}
	
}