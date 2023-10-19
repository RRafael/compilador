package Final;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EspecificaçãoIndividual {

    static int i = 0;
    static final String[] palavrasReservadas = {"programa",
        "se", "entao", "senao", "enquanto", "faca", "ate", "repita", "inteiro",
        "real", "caractere", "caso", "escolha",
        "fim_escolha", "procedimento", "funcao", "de", "para", "var"};

    public static boolean consultaPalavraReservada(String lexema) {
        for (int J = 0; J < palavrasReservadas.length; J++) {
            if (lexema.equals(palavrasReservadas[J])) {
                return true;
            }
        }
        return false;
    }

    public static void identificadores_PalavrasReservadas(String texto) {

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
                System.out.println(texto + " -> palavra reservada");
            } else {
                System.out.println(texto + " -> identificador");
            }
        } else {
            System.out.println(texto + " -> identificador invalida");
        }
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
            System.out.println(lexema + " -> comentario");
        } else {
            System.out.println(lexema + " -> comentario invalido");
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
            System.out.println(lexema + " -> comentario varias linhas");
        } else {
            System.out.println(lexema + " -> comentario invalido");
        }
    }

    public static void simbolosEspeciais(String lexema) {
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
            System.out.println(lexema + " -> simbolo Especial");
        } else {
            System.out.println(lexema + " -> simbolo especial invalido");
        }
    }

    public static void numeros(String lexema) {
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
            System.out.println(lexema + " -> digito");
        } else {
            System.out.println(lexema + " -> digito invalido");
        }

    }

    public static void incrementa(String lexema) {
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
            System.out.println(lexema + " -> incremento");
        } else {
            System.out.println(lexema + " -> incremento invalido");
        }

    }

    public static void decrementa(String lexema) {
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
            System.out.println(lexema + " -> decremento");
        } else {
            System.out.println(lexema + " -> decremento invalido");
        }

    }

    public static void main(String args[]) throws FileNotFoundException, IOException {

        RandomAccessFile Arquivo = new RandomAccessFile(
                "c:\\Users\\Rafael\\Desktop\\entrada.txt", "r");
        String texto = "";
        String linha = Arquivo.readLine();

        while (linha != null) {
            texto = texto + linha + '\n';
            linha = Arquivo.readLine();
        }

        while (i < texto.length()) {
            String lexema = "";
            if (texto.charAt(i) == '\n') {
            } else if (Character.isSpaceChar(texto.charAt(i))) {
            } else if (texto.charAt(i) == '\t') {
                // identificador ou palavras reservadas
            } else if (Character.isLetter(texto.charAt(i)) || texto.charAt(i) == '$') {
                lexema = lexema + texto.charAt(i++);
                while ((Character.isLetterOrDigit(texto.charAt(i)) || texto.charAt(i) == '_')) {
                    lexema = lexema + texto.charAt(i);
                    i++;
                }
                identificadores_PalavrasReservadas(lexema);
                i--;
                // comentario varias linhas
            } else if (texto.charAt(i) == '+' && texto.charAt(i + 1) == '+') {
                lexema = lexema + texto.charAt(i++);
                lexema = lexema + texto.charAt(i++);
                incrementa(lexema);
                i--;
            } else if (texto.charAt(i) == '-' && texto.charAt(i + 1) == '-') {
                lexema = lexema + texto.charAt(i++);
                lexema = lexema + texto.charAt(i++);
                decrementa(lexema);
                i--;
            } else if (texto.charAt(i) == '/' && texto.charAt(i + 1) == '*') {
                boolean fimComentario = false;
                while (fimComentario == false && i < texto.length()) {
                    lexema = lexema + texto.charAt(i++);
                    lexema = lexema + texto.charAt(i++);
                    while (texto.charAt(i) != '*' || i < texto.length()) {
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
                i--;
                // comentario uma linha
            } else if (texto.charAt(i) == '@' && texto.charAt(i + 1) == '@') {
                while (texto.charAt(i) != '\n') {
                    lexema = lexema + texto.charAt(i);
                    i++;
                }
                comentariosUmaLinha(lexema);
                i--;
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
                    simbolosEspeciais(lexema);
                } else if ((texto.charAt(i) == '>' || texto.charAt(i) == ':') && texto.charAt(i + 1) == '=') {
                    lexema = lexema + texto.charAt(i++);
                    lexema = lexema + texto.charAt(i++);
                    simbolosEspeciais(lexema);
                } else {
                    lexema = lexema + texto.charAt(i++);
                    simbolosEspeciais(lexema);
                }
                i--;
            } else if (Character.isDigit(texto.charAt(i))) {
                while (Character.isDigit(texto.charAt(i))) {
                    lexema = lexema + texto.charAt(i++);
                }
                numeros(lexema);
                i--;
            }
            i++;
        }
    }
}
