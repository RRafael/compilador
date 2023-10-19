
public class AutomatosCompilador {

    public void identificadores_PalavrasReservadas() {

        final int q0 = 0, q1 = 1, q2 = 2, q3 = 3, q4 = 4, q5 = 5, q6 = 6, q7 = 7, q8 = 8;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q0:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q1;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q1:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q4;
                    } else if (palavra[i] == '_') {
                        estadoAtual = q3;
                    } else if (Character.isDigit(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isLetterOrDigit(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q5;
                    } else if (Character.isDigit(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q5:
                    if (palavra[i] == '_') {
                        estadoAtual = q6;
                    } else if (Character.isDigit(palavra[i])) {
                        estadoAtual = q2;
                    } else if (Character.isLetter(palavra[i])) {
                        estadoAtual = q7;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q6:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q6;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q7:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q7;
                    } else if (Character.isDigit(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (Character.isLetterOrDigit(palavra[i])) {
                        estadoAtual = q2;
                    }else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q4 || estadoAtual == q2 || estadoAtual == q5 || estadoAtual == q6 || estadoAtual == q7) {
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }
    }

    public void identificadores() {

        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q1:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (Character.isLetterOrDigit(palavra[i])) {
                        estadoAtual = q4;
                    } else if (palavra[i] == '_') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isLetterOrDigit(palavra[i])) {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (Character.isLetterOrDigit(palavra[i])) {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q4 || estadoAtual == q2) {
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }
    }

    public void palavrasReservadas() {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4, q5 = 5;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q1:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (Character.isLetter(palavra[i]) || palavra[i] == '_') {
                        estadoAtual = q5;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q5:
                    if (Character.isLetter(palavra[i])) {
                        estadoAtual = q5;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q3 || estadoAtual == q4 || estadoAtual == q5) {
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }
    }

    public void simbolosEspeciais() {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q1:
                    if (palavra[i] == '>' || palavra[i] == ':') {
                        estadoAtual = q3;
                    } else if (palavra[i] == '<') {
                        estadoAtual = q4;
                    } else if (palavra[i] == ';' || palavra[i] == ',' || palavra[i] == '.' || palavra[i] == '-' || palavra[i] == '*' || palavra[i] == ')' || palavra[i] == '(' || palavra[i] == '}' || palavra[i] == '{' || palavra[i] == '@' || palavra[i] == '/' || palavra[i] == '+') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;

                case q3:
                    if (palavra[i] == '=') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (palavra[i] == '=' || palavra[i] == '>') {
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
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }
    }

    public void comentariosUmaLinha() {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q1:
                    if (palavra[i] == '@') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (palavra[i] == '@') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isDefined(palavra[i])) {
                        estadoAtual = q3;
                    } else if (palavra[i] == '\n') {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q4) {
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }

    }

    public void comentariosVariasLinhas() {
        final int q1 = 1, q2 = 2, q3 = 3, q4 = 4, q5 = 5;
        int estadoAtual = q1;
        char[] palavra = new char[]{'1', '2', '3', 'A', 'B', 'C'};

        String a = new String(palavra);

        for (int i = 0; i < a.length(); i++) {
            switch (estadoAtual) {
                case q1:
                    if (palavra[i] == '/') {
                        estadoAtual = q2;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q2:
                    if (palavra[i] == '*') {
                        estadoAtual = q3;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q3:
                    if (Character.isDefined(palavra[i]) || palavra[i] == '\n') {
                        estadoAtual = q3;
                    } else if (palavra[i] == '*') {
                        estadoAtual = q4;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                case q4:
                    if (Character.isDefined(palavra[i]) || palavra[i] == '\n') {
                        estadoAtual = q3;
                    } else if (palavra[i] == '*') {
                        estadoAtual = q4;
                    } else if (palavra[i] == '/') {
                        estadoAtual = q5;
                    } else {
                        estadoAtual = -1;
                    }
                    break;
                default:
                    break;
            }
        }

        if (estadoAtual == q5) {
            System.out.println("Identificador Reconhecida");
        } else {
            System.out.println("Identificador Nao Reconhecida");
        }
    }

    public static void main(String args[]) {
        AutomatosCompilador a = new AutomatosCompilador();
        a.identificadores();
    }
}
