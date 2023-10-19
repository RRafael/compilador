
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeituraArquivo {

    public static void main(String[] args) throws FileNotFoundException {

        RandomAccessFile arquivo = new RandomAccessFile("c:\\Users\\Rafael\\Desktop\\Programa.txt", "r");

        String s = "Rafael da cruz arruda:";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ':') {
                System.out.println("espaco em branco");
            }
        }
        try {

//faz a leitura do arquivo


            System.out.println(arquivo.length());


//equanto houver mais linhas
            // while (br.ready()) {
//lê a proxima linha
            //  String linha = br.readLine();

//faz algo com a linha
            // System.out.println(linha);
            // }
            int caracter, cont = 0;

            char array[] = new char[10];

            for (int i = 0; i < 9; i++) {
                array[i] = 'a';
            }

            for (int i = 0; i < 10; i++) {
                if (array[i] == '\0') {
                    System.out.println("Quebra de Linha");
                }
            }


            /*
            while ((caracter = arquivo.read()) != -1) {
            System.out.print((char) caracter);  //ler caracter por caracter o texto
            if ((char) caracter == '\n') {
            cont++;   //conta a quantidade de quebra de linhas
            }
            }
             */
            arquivo.close();
            System.out.println(cont);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
