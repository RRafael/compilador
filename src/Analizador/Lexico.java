package Analizador;
//rafael
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Lexico {

    static int i = 0;
    static String texto = "";
    static RandomAccessFile Arquivo;
    static int linha = 1;

    public Lexico() throws FileNotFoundException, IOException {
        Arquivo = new RandomAccessFile("c:\\Users\\Rafael\\Desktop\\entrada.txt", "r");
        String linhas = Arquivo.readLine();
        while (linhas != null) {
            texto = texto + linhas + '\n';
            linhas = Arquivo.readLine();
        }
    }

    static final String[] palavrasReservadas = {"programa",
        "se", "entao", "senao", "enquanto", "faca", "ate", "repita", "inteiro",
        "real", "caractere", "caso", "escolha", "leia", "imprima",
        "fim", "procedimento", "funcao", "do", "tipo", "para", "var", "inicio"};

    public static boolean consultaPalavraReservada(String lexema) {
        for (int J = 0; J < palavrasReservadas.length; J++) {
            if (lexema.equals(palavrasReservadas[J])) {
                return true;
            }
        }
        return false;
    }

    public static String identificadores_PalavrasReservadas(String texto) {

        final int q0 = 0, q1 = 1, q2 = 2, q3 = 3, q4 = 4, q5 = 5, q6 = 6, q7 = 7, q8 = 8, q9 = 9;
        int estadoAtual = q0;

        for (int j = 0; j < texto.length(); j++) {
            switch (estadoAtual) {
                case q0:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q1;
                    } else if (texto.charAt(j) == '$') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q1:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q4;
                    } else if (Character.isDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else if (texto.charAt(j) == '_') {
                        estadoAtual = q5;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isLetterOrDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q6;
                    } else if (Character.isDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q5:
                    if (Character.isLetterOrDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q6:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q7;
                    } else if (Character.isDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else if (texto.charAt(j) == '_') {
                        estadoAtual = q8;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q7:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q7;
                    } else if (Character.isDigit(texto.charAt(j))) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q8:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q9;

                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q9:
                    if (Character.isLetter(texto.charAt(j))) {
                        estadoAtual = q9;

                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q1 || estadoAtual == q3 || estadoAtual == q6 || estadoAtual == q4 || estadoAtual == q7 || estadoAtual == q9) {
            if (consultaPalavraReservada(texto)) {
                return texto;
                //System.out.println(texto + " -> palavra reservada");
            } else {
                return "identificador";
                //System.out.println(texto + " -> identificador");
            }
        } else {
            System.out.println("Erro Lexico - identificador '" + texto + "' invalido");
            System.exit(0);
            //System.out.println(texto + " -> identificador invalida");
        }
        return null;
    }

    public static void comentariosUmaLinha(String lexema) {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (lexema.charAt(j) == '@') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (lexema.charAt(j) == '@') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isDefined(lexema.charAt(j))) {
                        estadoAtual = q3;
                    } else if (lexema.charAt(j) == '\n') {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q3) {
            //System.out.println(lexema + " -> comentario uma linha");
        } else {
            System.out.println("Erro Lexico - comentario invalido");
            System.exit(0);
        }
    }

    public static void comentariosVariasLinhas(String lexema) {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4, q5 = 5;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (lexema.charAt(j) == '/') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (lexema.charAt(j) == '*') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (lexema.charAt(j) == '*') {
                        estadoAtual = q4;
                    } else if (Character.isDefined(lexema.charAt(j)) || lexema.charAt(j) == '\n') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (lexema.charAt(j) == '*') {
                        estadoAtual = q4;
                    } else if (lexema.charAt(j) == '/') {
                        estadoAtual = q5;
                    } else if (Character.isDefined(lexema.charAt(j)) || lexema.charAt(j) == '\n') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q5) {
            //System.out.println(lexema + " -> comentario varias linhas");
        } else {
            System.out.println("Erro Lexico - comentario nao possue tag de fechamento '*/' ");
            System.exit(0);
        }
    }

    public static String simbolosEspeciais(String lexema) {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (lexema.charAt(j) == '>' || lexema.charAt(j) == ':') {
                        estadoAtual = q3;
                    } else if (lexema.charAt(j) == '<') {
                        estadoAtual = q4;
                    } else if (lexema.charAt(j) == ';' || lexema.charAt(j) == ',' || lexema.charAt(j) == '.' || lexema.charAt(j) == '-' || lexema.charAt(j) == '*' || lexema.charAt(j) == ')' || lexema.charAt(j) == '(' || lexema.charAt(j) == '}' || lexema.charAt(j) == '{' || lexema.charAt(j) == '@' || lexema.charAt(j) == '/' || lexema.charAt(j) == '+') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (lexema.charAt(j) == '=') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (lexema.charAt(j) == '=' || lexema.charAt(j) == '>') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q3 || estadoAtual == q2 || estadoAtual == q4) {
            return lexema;
            //System.out.println(lexema + " -> simbolo Especial");
        } else {
            return lexema;
            //System.out.println(lexema + " -> simbolo especial invalido");
        }
    }

    public static String numeros(String lexema) {
        final int q1 = 1, q2 = 2;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (Character.isDigit(lexema.charAt(j))) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (Character.isDigit(lexema.charAt(j))) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q2) {
            return "numero";
        } else {
            System.out.println("Erro Lexico - digito invalido");
            System.exit(0);
        }
        return null;
    }

    public static String incrementa(String lexema) {
        final int q1 = 1, q2 = 2, q3 = 3;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (lexema.charAt(j) == '+') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (lexema.charAt(j) == '+') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q3) {
            return lexema;
        } else {
            System.out.println("Erro Lexico - operador incremento invalido");
            System.exit(0);
        }
        return null;
    }

    public static String decrementa(String lexema) {
        final int q1 = 1, q2 = 2, q3 = 3;
        int estadoAtual = q1;

        for (int j = 0; j < lexema.length(); j++) {
            switch (estadoAtual) {
                case q1:
                    if (lexema.charAt(j) == '-') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (lexema.charAt(j) == '-') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q3) {
            return lexema;
        } else {
            System.out.println("Erro Lexico - operador decremento invalido");
            System.exit(0);
        }
        return null;
    }

    public String enviaToken() throws FileNotFoundException, IOException {

        while (i < texto.length()) {
            String lexema = "";
            if (texto.charAt(i) == '\n') {
                linha++;
                i++;
            } else if (Character.isSpaceChar(texto.charAt(i))) {
                i++;
            } else if (texto.charAt(i) == '\t') {
                i++;
                // identificador ou palavras reservadas
            } else if (Character.isLetter(texto.charAt(i)) || texto.charAt(i) == '$') {
                lexema = lexema + texto.charAt(i++);
                while ((Character.isLetterOrDigit(texto.charAt(i)) || texto.charAt(i) == '_')) {
                    lexema = lexema + texto.charAt(i);
                    i++;
                }
                return identificadores_PalavrasReservadas(lexema);

                // comentario varias linhas
            } else if (texto.charAt(i) == '+' && texto.charAt(i + 1) == '+') {
                lexema = lexema + texto.charAt(i++);
                lexema = lexema + texto.charAt(i++);
                return incrementa(lexema);

            } else if (texto.charAt(i) == '-' && texto.charAt(i + 1) == '-') {
                lexema = lexema + texto.charAt(i++);
                lexema = lexema + texto.charAt(i++);
                return decrementa(lexema);

            } else if (texto.charAt(i) == '/' && texto.charAt(i + 1) == '*') {
                boolean fimComentario = false;
                while (fimComentario == false && i < texto.length()) {
                    lexema = lexema + texto.charAt(i++);
                    lexema = lexema + texto.charAt(i++);
                    while (texto.charAt(i) != '*' || i < texto.length()) {
                        if (texto.charAt(i) == '\n') {
                            linha++;
                        }
                        lexema = lexema + texto.charAt(i);
                        i++;
                        if (i == texto.length()) {
                            break;
                        }
                        if (texto.charAt(i) == '/' && texto.charAt(i - 1) == '*') {
                            lexema = lexema + texto.charAt(i++);
                            fimComentario = true;
                            break;
                        }
                    }
                }
                comentariosVariasLinhas(lexema);

                // comentario uma linha
            } else if (texto.charAt(i) == '@' && texto.charAt(i + 1) == '@') {
                while (texto.charAt(i) != '\n') {
                    lexema = lexema + texto.charAt(i);
                    i++;
                }
                comentariosUmaLinha(lexema);

                //simbolos especiais
            } else if (texto.charAt(i) == ':' || texto.charAt(i) == ';'
                    || texto.charAt(i) == ',' || texto.charAt(i) == '='
                    || texto.charAt(i) == '.' || texto.charAt(i) == '-'
                    || texto.charAt(i) == '*' || texto.charAt(i) == ')'
                    || texto.charAt(i) == '(' || texto.charAt(i) == '}'
                    || texto.charAt(i) == '{' || texto.charAt(i) == '@'
                    || texto.charAt(i) == '/' || texto.charAt(i) == '+'
                    || texto.charAt(i) == '>' || texto.charAt(i) == '<') {

                if (texto.charAt(i) == '<' && (texto.charAt(i + 1) == '>' || texto.charAt(i + 1) == '=')) {
                    lexema = lexema + texto.charAt(i++);
                    lexema = lexema + texto.charAt(i++);
                    return simbolosEspeciais(lexema);
                } else if ((texto.charAt(i) == '>' || texto.charAt(i) == ':') && texto.charAt(i + 1) == '=') {
                    lexema = lexema + texto.charAt(i++);
                    lexema = lexema + texto.charAt(i++);
                    return simbolosEspeciais(lexema);
                } else {
                    lexema = lexema + texto.charAt(i++);
                    return simbolosEspeciais(lexema);
                }

            } else if (Character.isDigit(texto.charAt(i))) {
                while (Character.isDigit(texto.charAt(i))) {
                    lexema = lexema + texto.charAt(i++);
                }
                return numeros(lexema);

            } else {
                i++;
            }
        }
        return null;
    }
}
