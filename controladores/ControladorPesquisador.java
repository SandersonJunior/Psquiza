package controladores;

import sistemaDePesquisas.Pesquisador;
import validadores.Validador;

import java.util.HashMap;


public class ControladorPesquisador {

    private static HashMap<String, Pesquisador> pesquisadores = new HashMap<>();


    public static void cadastraPesquisador(String nome, String funcao, String biografia, String email, String foto) {
        Validador.validaNome(nome);
        Validador.validaEmail(email);
        Validador.validaBiografia(biografia);
        Validador.validaFoto(foto);
        Validador.validaFuncao(funcao);

        Pesquisador novoPesquisador = new Pesquisador(nome, funcao, biografia, email, foto);
        pesquisadores.put(email, novoPesquisador);


    }

    public static void alteraPesquisador(String email, String atributo, String novoValor) {
        Validador.validaEmail(email);
        if (!existeAtributo(atributo)) {
            throw new IllegalArgumentException("Atributo invalido");
        }
        if (!pesquisadores.containsKey(email)) {
            throw new IllegalArgumentException("Pesquisador não encontrado");
        }
        if (atributo.equals("NOME")) {
            Validador.validaNome(novoValor);
            pesquisadores.get(email).setNome(novoValor);
        } else if (atributo.equals("FUNCAO")) {
            Validador.validaFuncao(novoValor);
            pesquisadores.get(email).setFuncao(novoValor);
        } else if (atributo.equals("BIOGRAFIA")) {
            Validador.validaBiografia(novoValor);
            pesquisadores.get(email).setBiografia(novoValor);
        } else if (atributo.equals("EMAIL")) {
            Validador.validaEmail(novoValor);
            pesquisadores.get(email).setEmail(novoValor);
        } else if (atributo.equals("FOTO")) {
            Validador.validaFoto(novoValor);
            pesquisadores.get(email).setFoto(novoValor);
        }
    }


    private static boolean existeAtributo(String atributo) {
        if (atributo.trim().equalsIgnoreCase("NOME") || atributo.trim().equalsIgnoreCase("BIOGRAFIA")
                || atributo.trim().equalsIgnoreCase("FOTO")
                || atributo.trim().equalsIgnoreCase("EMAIL")
                || atributo.trim().equalsIgnoreCase("FUNCAO")) {
            return true;
        }
        return false;
    }

    public static Boolean pesquisadorEhAtivo(String email) {
        Validador.validaEmail(email);
        if (pesquisadores.get(email).getStatus().equalsIgnoreCase("ativo")) {
            return true;
        }
        return false;
    }

    public static String exibePesquisador(String email) {
        Validador.validaEmail(email);
        if (pesquisadores.containsKey(email)) {
            return pesquisadores.get(email).toString();
        }
        throw new IllegalArgumentException("Pesquisador nao encontrado");

    }

    public static void ativaPesquisador(String email) {
        Validador.validaEmail(email);
        if (pesquisadores.get(email).getStatus().equalsIgnoreCase("ativo")) {
            throw new IllegalArgumentException("Pesquisador ja ativo");
        }
    }

    public static void desativaPesquisador(String email) {
        if (pesquisadores.get(email).getStatus().equalsIgnoreCase("inativo")) {
            throw new IllegalArgumentException("Pesquisador inativo");
        }
    }
}