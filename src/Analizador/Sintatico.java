package Analizador;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Sintatico {

    static String token;

    public static void obtemToken(Lexico l) throws IOException {
        token = l.enviaToken();
    }

    public static void declara_Tipo(Lexico l) throws IOException {
        obtemToken(l);

        do {
            if (!token.equals("identificador")) {
                System.out.println("Erro Sintatico: 'declaracao de tipo invalido' linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

            if (!token.equals("=")) {
                System.out.println("Erro Sintatico: '=' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

            if (!(token.equals("identificador") || token.equals("inteiro")
                    || token.equals("real")
                    || token.equals("caracter"))) {

                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

            if (!token.equals(";")) {
                System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

        } while (token.equals("identificador"));

    }

    public static void declara_Var(Lexico l) throws IOException {
        obtemToken(l);
        do {
            if (!token.equals("identificador")) {
                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

            if (token.equals(",")) {
                lista_Identificadores(l);
            }
            if (!token.equals(":")) {
                System.out.println("Erro Sintatico: ':' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (!(token.equals("inteiro")
                    || token.equals("real")
                    || token.equals("caracter"))) {
                System.out.println("Erro Sintatico: tipo de dado incorreto linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (!token.equals(";")) {
                System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

        } while (token.equals("identificador"));

    }

    public static void declaraFuncao(Lexico l) throws IOException {
        obtemToken(l);
        if (!token.equals("identificador")) {
            System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
            System.exit(0);
        }

        obtemToken(l);
        if (token.equals("(")) {
            parametrosFormais(l);
        }
        if (!token.equals(":")) {
            System.out.println("Erro Sintatico: ':' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        if (!token.equals("identificador")) {
            System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
            System.exit(0);
        }

        obtemToken(l);
        if (!token.equals(";")) {
            System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        Bloco(l);

    }

    public static void declaraProcedimento(Lexico l) throws IOException {
        obtemToken(l);

        if (!token.equals("identificador")) {
            System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        if (token.equals("(")) {
            parametrosFormais(l);
        }

        if (!token.equals(";")) {
            System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        Bloco(l);

    }

    public static void lista_Identificadores(Lexico l) throws IOException {

        while (token.equals(",")) {
            obtemToken(l);
            if (!token.equals("identificador")) {
                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (token.equals("identificador")) {
                System.out.println("Erro Sintatico: ',' esperado linha = " + l.linha);
                System.exit(0);
            }
        }
    }

    public static void parametrosFormais(Lexico l) throws IOException {

        do {
            obtemToken(l);
            if (token.equals("var")) {
                obtemToken(l);
                if (!token.equals("identificador")) {
                    System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                    System.exit(0);
                }
                obtemToken(l);
                lista_Identificadores(l);
            } else if (token.equals("identificador")) {
                obtemToken(l);
                if (token.equals(",")) {
                    lista_Identificadores(l);
                }
            } else {
                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }

            if (!token.equals(":")) {
                System.out.println("Erro Sintatico: ':' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (!token.equals("identificador")) {
                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

        } while (token.equals(";"));

        if (!token.equals(")")) {
            System.out.println("Erro Sintatico: ')' esperado");
            System.exit(0);
        }
        obtemToken(l);
    }

    public static void Bloco(Lexico l) throws IOException {

        if (token.equals("tipo")) {
            declara_Tipo(l);
        }
        if (token.equals("var")) {
            declara_Var(l);
        }

        while (token.equals("procedimento") || token.equals("funcao")) {
            do {
                if (token.equals("funcao")) {
                    declaraFuncao(l);
                } else {
                    declaraProcedimento(l);
                }
            } while (token.equals("procedimento") || token.equals("funcao"));

        }
        comandoComposto(l);
    }

    public static void listaExpressao(Lexico l) throws IOException {
        expressao(l);
        while (token.equals(";")) {
            expressao(l);
        }
    }

    public static void expressao(Lexico l) throws IOException {

        expressaoSimples(l);

        if (token.equals("=") || token.equals("<>") //relacao
                || token.equals("<") || token.equals("<=")
                || token.equals(">") || token.equals(">=")) {
            expressaoSimples(l);
        }
    }

    public static void expressaoSimples(Lexico l) throws IOException {
        termo(l);
        while (token.equals("+") || token.equals("-") || token.equals("ou")) {
            termo(l);
        }
    }

    public static void termo(Lexico l) throws IOException {

        fator(l);
        while (token.equals("*") || token.equals("div") || token.equals("e")) {
            fator(l);
        }
    }

    public static void comandoSemRotulo(Lexico l) throws IOException {

        if (token.equals("identificador")) {
            obtemToken(l);
            if (token.equals(":=")) {
                expressao(l);
            }
            if (token.equals("(")) {
                listaExpressao(l);
                if (!token.equals(")")) {
                    System.out.println("Erro Sintatico: ')' esperado linha = " + l.linha);
                    System.exit(0);
                }
                obtemToken(l);
            }
        } else if (token.equals("se")) {
            expressao(l);
            if (!token.equals("entao")) {
                System.out.println("Erro Sintatico: 'entao' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (token.equals(";")) {
                return;
            }
            comandoSemRotulo(l);
            if (token.equals(";")) {
                obtemToken(l);
            }
            if (token.equals("senao")) {
                obtemToken(l);
                comandoSemRotulo(l);
            }
        } else if (token.equals("enquanto")) {
            expressao(l);
            if (!token.equals("do")) {
                System.out.println("Erro Sintatico: 'do' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            comandoSemRotulo(l);
        } else if (token.equals("leia")) {
            obtemToken(l);
            if (!token.equals("(")) {
                System.out.println("Erro Sintatico: '(' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (!token.equals("identificador")) {
                System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
            if (token.equals(",")) {
                lista_Identificadores(l);
            }
            if (!token.equals(")")) {
                System.out.println("Erro Sintatico: ')' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
        } else if (token.equals("imprima")) {
            obtemToken(l);
            if (!token.equals("(")) {
                System.out.println("Erro Sintatico: '(' esperado linha = " + l.linha);
                System.exit(0);
            }
            listaExpressao(l);

            if (!token.equals(")")) {
                System.out.println("Erro Sintatico: ')' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);
        }
    }

    public static void comandoComposto(Lexico l) throws IOException {

        if (!token.equals("inicio")) {
            System.out.println("Erro Sintatico: 'inicio' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        do {
            comandoSemRotulo(l);
            if (!token.equals(";")) {
                System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
                System.exit(0);
            }
            obtemToken(l);

        } while (!token.equals("fim"));
        obtemToken(l);
    }

    public static void fator(Lexico l) throws IOException {
        obtemToken(l);
        if (token.equals("+") || token.equals("-")) {
            obtemToken(l);
        }

        if (token.equals("identificador")) {
            obtemToken(l);
            if (token.equals("(")) {
                listaExpressao(l);
                if (!token.equals(")")) {
                    System.out.println("Erro Sintatico: ')' esperado linha = " + l.linha);
                    System.exit(0);
                }
                obtemToken(l);
            }
        } else if (token.equals("numero")) {
            obtemToken(l);
        } else if (token.equals("(")) {
            expressao(l);
            if (!token.equals(")")) {
                System.out.println("Erro Sintatico: ')' esperado");
                System.exit(0);
            }
        } else {
            System.out.println("Erro Sintatico: 'expressao' esperado linha = " + Lexico.linha);
            System.exit(0);
        }
    }

    public static void Sintatico(Lexico l) throws IOException {
        obtemToken(l);
        if (!"programa".equals(token)) {
            System.out.println("Erro Sintatico: 'programa' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        if (!"identificador".equals(token)) {
            System.out.println("Erro Sintatico: 'identificador' esperado linha = " + l.linha);
            System.exit(0);
        }
        obtemToken(l);
        if (!";".equals(token)) {
            System.out.println("Erro Sintatico: ';' esperado linha = " + l.linha);
            System.exit(0);
        }

        obtemToken(l);

        Bloco(l);

        if (!".".equals(token)) {
            System.out.println("Erro Sintatico: '.' esperado linha = " + l.linha);
            System.exit(0);
        }
        System.out.println("Seu Código esta Correto");
    }

    public static void main(String args[]) throws IOException {
        Sintatico(new Lexico());
    }
}
