package validadores;

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

}
