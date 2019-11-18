package validadores;

public class Validador {

	public void validadorString(String palavra, String mensagem) {
		if (palavra == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(palavra)) {
			throw new IllegalArgumentException(mensagem);
		}

	}

	public void validadorInt(Integer numero, String mensagem) {
		if (numero == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(numero.toString())) {
			throw new IllegalArgumentException(mensagem);

		}
	}

	public void validadorTipoObjetivo(String palavra, String mensagem) {
		if (!palavra.equals("GERAL") && !palavra.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validadorTipoPesquisa(String palavra, String mensagem) {
		if (!palavra.equals("PROBLEMA") && !palavra.equals("OBJETIVOS") && !palavra.equals("PESQUISA")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validadorTamanhoCampoInteresse(String campoInteresse, String mensagem) {
		if (campoInteresse.length() > 255 || campoInteresse.length() < 3) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validadorQuantidadeCampoInteresse(String campoInteresse, String mensagem) {
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

	private boolean possuiDigitoOuLetra(String nome) {
		for (char letra : nome.toCharArray()) {
			if (Character.isAlphabetic(letra) || Character.isDigit(letra))
				return true;
		}
		return false;
	}

	public void validaEmail(String email) {
		validadorString(email, "Campo email nao pode ser nulo ou vazio.");

		if (!email.contains("@"))
			throw new IllegalArgumentException("Formato de email invalido.");

		int indexArroba = email.indexOf("@");

		if (indexArroba == 0 || indexArroba == email.length() - 1)
			throw new IllegalArgumentException("Formato de email invalido.");

		String antes = email.substring(0, indexArroba);
		String depois = email.substring(indexArroba + 1, email.length());

		if (!possuiDigitoOuLetra(antes) || !possuiDigitoOuLetra(depois))
			throw new IllegalArgumentException("Formato de email invalido.");

	}

	public void validaFoto(String foto) {
		validadorString(foto, "Campo fotoURL nao pode ser nulo ou vazio.");

		if (foto.length() < 7) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
		if (foto.substring(0, 7).equals("http://") || foto.substring(0, 8).equals("https://")) {
			int index = foto.substring(0, 7).equals("http://") ? 7 : 8;
			if (!possuiDigitoOuLetra(foto.substring(index, foto.length()))) {
				throw new IllegalArgumentException("Formato de foto invalido.");
			}
		}

		else {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	public void validaViabilidade(int valor, String mensagem) {
		validadorInt(valor, "Campo viabilidade nao pode ser nulo ou vazio.");

		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	public void validaAderencia(int valor, String mensagem) {
		validadorInt(valor, "Campo aderencia nao pode ser nulo ou vazio.");

		if (valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}