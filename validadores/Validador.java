package validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
	
	public void validadorString(String palavra, String mensagem) {
		if("".equals(palavra.trim()) || palavra == null) {
			throw new IllegalArgumentException(mensagem);
		}
		
	}
	
	public void validadorTamanhoCampoInteresse(String campoInteresse, String mensagem) {
		if(campoInteresse.length() > 255 || campoInteresse.length() < 3) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	public void validadorQuantidadeCampoInteresse(String campoInteresse, String mensagem) {
		String[] lista = campoInteresse.split(",");
		if (lista.length > 4) 
			throw new IllegalArgumentException(mensagem); 
		else {
			for(int i = 0; i <  lista.length; i++) {
				if("".equals(lista[i].trim()) || lista[i].equals(" ")) {
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

	private boolean isEmpty(Integer valor) {
		return Integer.toString(valor).trim().equals("");
	}

	public static void validaNome(String nome){
		if (isNull(nome) || isEmpty(nome)) {
			throw new IllegalArgumentException("Campo nome nao pode ser nulo ou vazio.");
		}
	}

	public static void validaEmail(String email){
		if (isNull(email) || isEmpty(email)) {
			throw new IllegalArgumentException("Campo email nao pode ser nulo ou vazio");
		}
		boolean emailValido = false;
		if (email != null && email.length() > 0) {
			String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)";
			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				emailValido = true;
			}
			else{
				throw new IllegalArgumentException("Formato de email invalido");
			}

		}

	}


	public static void validaFuncao(String funcao){
		if (isNull(funcao) || isEmpty(funcao){
			throw new IllegalArgumentException("Campo funcao nao pode ser nulo ou vazio.");
		}
	}

	public static void validaBiografia(String biografia){
		if (isNull(biografia) || isEmpty(biografia){ throw new IllegalArgumentException("Campo biografia nao pode ser nulo ou vazio.");
		}
	}

	public static void validaFoto(String foto){
		if (isNull(foto) || isEmpty(foto)){
			throw new IllegalArgumentException("Campo foto nao pode ser nulo ou vazio.");
		}
		if (foto.length() >= 6){
			boolean fotoValida = false;
			String expressao = "^((((https?|http?)://)|(mailto:|news:))" + "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)";
			Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(foto);
			if(matcher.matches()){
				fotoValida = true;
			}
			else {
				throw new IllegalArgumentException("Formato de foto invalido");


			}
