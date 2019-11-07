package validadores;

public class Validador {

	public void validadorString(String palavra, String mensagem) {
		if ("".equals(palavra.trim()) || palavra == null) {
			throw new IllegalArgumentException(mensagem);
		}

	}
	
	public void validadorTipo(String palavra, String mensagem) {
		if(!palavra.equals("GERAL") && !palavra.equals("ESPECIFICO")) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	public void validadorTipo2(String palavra, String mensagem) {
		if(!palavra.equals("PROBLEMA") && !palavra.equals("OBJETIVOS") && !palavra.equals("PESQUISA")) {
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

	private static boolean isNull(String valor) {
		return valor == null;
	}

	private static boolean isNull(Integer valor) {
		return valor == null;
	}

	private static boolean isEmpty(String valor) {
		return valor.trim().equals("");
	}

	public static void validaNome(String nome) {
		if (isNull(nome) || isEmpty(nome)) {
			throw new IllegalArgumentException("Campo nome nao pode ser nulo ou vazio.");
		}
	}

	private static boolean possuiDigitoOuLetra(String nome) {
		for (char letra : nome.toCharArray()) {
			if (Character.isAlphabetic(letra) || Character.isDigit(letra))
				return true;
		}
		return false;
	}

	public static void validaEmail(String email) {
		if (isNull(email) || isEmpty(email)) {
			throw new IllegalArgumentException("Campo email nao pode ser nulo ou vazio.");
		}

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

	public static void validaFuncao(String funcao) {
		if (isNull(funcao) || isEmpty(funcao)) {
			throw new IllegalArgumentException("Campo funcao nao pode ser nulo ou vazio.");
		}
	}

	public static void validaBiografia(String biografia) {
		if (isNull(biografia) || isEmpty(biografia)) {
			throw new IllegalArgumentException("Campo biografia nao pode ser nulo ou vazio.");
		}

	}

	public static void validaFoto(String foto) {
		if (isNull(foto) || isEmpty(foto)) {
			throw new IllegalArgumentException("Campo fotoURL nao pode ser nulo ou vazio.");
		}

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

//		if (foto.length() >= 6) {
//			boolean fotoValida = false;
//			String expressao = "^((((https?|http?)://)|(mailto:|news:))"
//					+ "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)";
//			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
//			Matcher matcher = pattern.matcher(foto);
//			if (matcher.matches()) {
//				fotoValida = true;
//			} else {
//				throw new IllegalArgumentException("Formato de foto invalido.");
//			}
		
		/*############
		 * validadores de objetivos e problemas 
									############
		*/ 
//		}

	public void validaViabilidade(int valor, String mensagem) {
		if(isNull(valor) || isEmpty(Integer.toString(valor))) {
			throw new IllegalArgumentException("Campo viabilidade nao pode ser nulo ou vazio.");
		}
		else if(valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);			
		}
	}

	public void validaAderencia(int valor, String mensagem) {
		if(isNull(valor) || isEmpty(Integer.toString(valor))) {
			throw new IllegalArgumentException("Campo aderencia nao pode ser nulo ou vazio.");
		}
		else if(valor > 5 || valor < 1) {
			throw new IllegalArgumentException(mensagem);			
		}
	}
		
}