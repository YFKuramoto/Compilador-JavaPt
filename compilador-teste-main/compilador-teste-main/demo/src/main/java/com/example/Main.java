package com.example;

import java.io.StringReader;

public class Main { // se o arquivo se chamar App.java, mantenha "public class App"
    public static void main(String[] args) {
        testar("1. Expressao aritmetica",
            "escreva 2 + 3 * 4"
        );

        testar("2. Programa completo",
            "leia x;\n" +
            "se x > 0 entao\n" +
            "    enquanto x > 0 faca\n" +
            "        escreva x * 2;\n" +
            "        x := x - 1\n" +
            "    fim\n" +
            "senao\n" +
            "    escreva 0\n" +
            "fim"
        );

        testar("3. Erro sintatico proposital",
            "se x > 0 escreva x fim"
        );
    }

    private static void testar(String nomeTeste, String codigoFonte) {
        System.out.println("=== " + nomeTeste + " ===");
        try {
            CompiladorExp parser = new CompiladorExp(new StringReader(codigoFonte));
            parser.programa();
            System.out.println("=> Sintaxe Correta!\n");
        } catch (ParseException e) {
            System.out.println("[ERRO SINTATICO] " + e.getMessage() + "\n");
        } catch (TokenMgrError e) {
            System.out.println("[ERRO LEXICO] " + e.getMessage() + "\n");
        }
    }
}