# Compilador JavaPt

Compilador e interpretador educacional desenvolvido em **Java** e **JavaCC** para a disciplina de **Teoria da Computação e Compiladores**.

## 1. Apresentação da Linguagem

A **JavaPt** é uma linguagem de programação imperativa estruturada, inspirada na linguagem clássica **TINY**, projetada com palavras reservadas em português para facilitar o aprendizado de construção de compiladores descendentes recursivos ($LL(1)$).

Recursos suportados pela linguagem:
- Declaração implícita e atribuição dinâmica de variáveis numéricas (`:=`);
- Expressões aritméticas com precedência padrão (`+`, `-`, `*`, `/`) e agrupamento por parênteses;
- Expressões relacionais (`<`, `>`, `=`, `<>`);
- Estruturas condicionais simples e compostas (`se ... entao ... fim` e `se ... entao ... senao ... fim`);
- Estrutura de repetição condicional (`enquanto ... faca ... fim`);
- Comandos de entrada e saída padrão (`leia` e `escreva`);
- Comentários de linha (`//`) e de bloco (`/* ... */`).

---

## 2. Especificação Formal da Gramática (EBNF)

A gramática abaixo foi fatorada e estruturada sem recursão à esquerda para compatibilidade direta com análise sintática preditiva Top-Down no JavaCC.

### 2.1 Definição Léxica (Tokens)

```ebnf
LETRA         ::= ['a'-'z', 'A'-'Z']
DIGITO        ::= ['0'-'9']
NUMERO        ::= DIGITO+ ( "." DIGITO+ )?
IDENTIFICADOR ::= LETRA ( LETRA | DIGITO )*

(* Palavras Reservadas *)
SE            ::= "se"
ENTAO         ::= "entao"
SENAO         ::= "senao"
FIM           ::= "fim"
ENQUANTO      ::= "enquanto"
FACA          ::= "faca"
LEIA          ::= "leia"
ESCREVA       ::= "escreva"

(* Operadores e Delimitadores *)
ATRIB         ::= ":="
PONTO_VIRGULA ::= ";"
APARENTESES   ::= "("
FPARENTESES   ::= ")"
DIFERENTE     ::= "<>"
MENOR         ::= "<"
MAIOR         ::= ">"
IGUAL         ::= "="
MAIS          ::= "+"
MENOS         ::= "-"
MULT          ::= "*"
DIV           ::= "/"
```

### 2.2 Produções Sintáticas

```ebnf
programa      ::= sequenciaDecl EOF

sequenciaDecl ::= declaracao ( ";" declaracao )*

declaracao    ::= condDecl
                | repetDecl
                | atribDecl
                | leitDecl
                | escrDecl

condDecl      ::= "se" expressao "entao" sequenciaDecl ( "senao" sequenciaDecl )? "fim"

repetDecl     ::= "enquanto" expressao "faca" sequenciaDecl "fim"

atribDecl     ::= IDENTIFICADOR ":=" expressao

leitDecl      ::= "leia" IDENTIFICADOR

escrDecl      ::= "escreva" expressao

expressao     ::= exp ( ( "<" | ">" | "=" | "<>" ) exp )?

exp           ::= termo ( ( "+" | "-" ) termo )*

termo         ::= fator ( ( "*" | "/" ) fator )*

fator         ::= "(" expressao ")"
                | NUMERO
                | IDENTIFICADOR
```

---

## 3. Estrutura do Repositório

```text
Compilador-JavaPt/
├── pom.xml                                  # Configuração do Maven e javacc-maven-plugin
├── README.md                                # Documentação formal e instruções do projeto
├── exemplos/                                # Programas de teste válidos e inválidos
│   ├── exemplo1.txt
│   ├── exemplo2.txt
│   ├── exemplo3.txt
│   ├── exemplo4.txt
│   └── exemplo5.txt
└── src/
    └── main/
        ├── java/com/example/Main.java       # Classe executora dos programas de exemplo
        └── javacc/CompiladorExp.jj          # Especificação léxica, sintática e AST no JavaCC
```

---

## 4. Instruções de Compilação e Execução

### Pré-requisitos
- **Java JDK 17** ou superior;
- **Apache Maven 3.8+** (ou ambiente GitHub Codespaces / VS Code com *Extension Pack for Java*).

### Passo a passo pelo Terminal


1. **Gerar o parser JavaCC e compilar o projeto:**
   ```bash
   mvn compile
   ```

2. **Executar o código inserido no `Main.java`:**
   ```bash
   java -cp target/classes com.example.Main
   ```
   *(Para testar outro programa da pasta `exemplos/`, copie o conteúdo do arquivo `.txt`, cole dentro da variável `codigo` em `src/main/java/com/example/Main.java`, rode `mvn compile` e execute novamente.)*

3. **Executar colando o código diretamente no console (`System.in`):**
   ```bash
   java -cp target/classes com.example.CompiladorExp
   ```
   *(Cole o código puro no terminal, pressione **Enter** e envie o fim de arquivo com **Ctrl + D** no Linux/Codespaces ou **Ctrl + Z** + **Enter** no Windows.)*

### Execução pelo VS Code / GitHub Codespaces

1. Sempre que alterar `CompiladorExp.jj`, execute `mvn compile` no terminal (ou clique em **Maven → Lifecycle → compile** na barra lateral).
2. Abra o arquivo desejado na pasta `exemplos/`, copie o conteúdo e cole dentro de `String codigo = """ ... """;` em `src/main/java/com/example/Main.java`.
3. Clique em **Run** acima do método `main` em `Main.java`. Caso o programa utilize o comando `leia`, digite o número no terminal inferior e pressione **Enter**.

---

## 5. Matriz de Responsabilidade

| Integrante | Responsabilidades e Módulos Desenvolvidos |
| :--- | :--- |
| Yuri Freire Kuramoto | Configuração do ambiente Maven/JavaCC, especificação léxica (`TOKEN`/`SKIP`), regras sintáticas $LL(1)$ e precedência de operadores, ações semânticas e estruturas de execução (`se`/`enquanto`, variáveis e E/S), suíte de testes (`exemplos/`) e documentação formal. |
