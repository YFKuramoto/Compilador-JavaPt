package com.example;

import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        // Cole aqui o codigo de qualquer arquivo da pasta exemplos/ para testar
        String codigo = """
                a := 40;
                b := 20;

                se a <> b entao
                     se a < b entao
                        escreva b - a;
                    senao
                        escreva a - b / 2;
                    fim;
                senao
                    escreva 0;
                fim;
                """;

        try {
            CompiladorExp parser = new CompiladorExp(new StringReader(codigo));
            parser.programa();
            System.out.println("\n=> Programa executado com sucesso!");
        } catch (ParseException e) {
            System.out.println("\n[ERRO SINTATICO] " + e.getMessage());
        } catch (TokenMgrError e) {
            System.out.println("\n[ERRO LEXICO] " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("\n[ERRO SEMANTICO] " + e.getMessage());
        }
    }
}