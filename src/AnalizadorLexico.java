
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class AnalizadorLexico {

    RandomAccessFile Arquivo;
    final int NUM = 98;
    final int TAM = 32;
    final int FIMSTRING = '\0';
    final int IDENTIFICADOR = 99;    
    static final String[] palavrasReservadas = {"programa",
    "se", "entao", "senao", "enquanto", "faca", "ate", "repita", "inteiro", 
    "real", "caractere","caso", "escolha",
    "fim_escolha", "procedimento", "funcao", "de", "para" };    
    int contLinha = 1;
    char[] lexBuffer = new char[TAM];
    int p;
    char t;

    public int consultaPalavra() {
        int i = 0, cod = -1;
        StringBuilder buffer = new StringBuilder();

        while (Character.isLetterOrDigit(lexBuffer[i])) {
            buffer.append(lexBuffer[i]);
            i++;
        }

        for (i = 0; i < 5; i++) {
            if (buffer.toString().equals(palavrasReservadas[i])) {
                cod = i;
            }
        }
        return cod;
    }

    public void classifica(int p) {
        switch (p) {
            case NUM:
                System.out.println(" Digito");
                break;
            case IDENTIFICADOR:
                System.out.println(" Ideintificador");
                break;
            default:
                System.out.println(" Palavra reservada");
                break;
        }
    }

    public void lexan() throws FileNotFoundException, IOException {

        Arquivo = new RandomAccessFile("c:\\Users\\Rafael\\Desktop\\Programa.txt", "r");

        try {
            int caracter;            

            while ((caracter = Arquivo.read()) != -1) {
                System.out.print((char) caracter);
                t = (char) caracter;
                if (Character.isSpaceChar(t)) {
                } else if (t == '\t') {
                } else if (t == '\n') {
                    contLinha++;
                } else if (Character.isDigit(t)) {
                    classifica(NUM);
                } else if (Character.isLetter(t)) {
                    int b = 0;
                    while (Character.isLetterOrDigit(t)) {
                        lexBuffer[b] = t;
                        caracter = Arquivo.read();
                        System.out.print((char) caracter);
                        t = (char) caracter;
                        b = b + 1;
                        if (b >= TAM) {
                            System.out.println("erro");
                        }
                    }
                    lexBuffer[b] = FIMSTRING;
                    p = consultaPalavra();
                    if (p != -1) {
                        classifica(p);
                    } else {
                        classifica(IDENTIFICADOR);
                    }
                }
            }
            Arquivo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        AnalizadorLexico a = new AnalizadorLexico();
        a.lexan();
        System.out.println("\nQuantidade de Linhas : " + a.contLinha);
    }
}
