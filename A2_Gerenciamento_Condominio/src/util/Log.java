package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Log {
    private static final File ARQUIVO = new File("log/log.txt");

    public static void gravar(String texto) throws Exception {
        try {
            // Certifique-se de que o diretório do arquivo existe, sem recriar se já existir
            File parentDir = ARQUIVO.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Usar FileWriter no modo de append para não sobrescrever o arquivo
            FileWriter file = new FileWriter(ARQUIVO, true);
            PrintWriter print = new PrintWriter(file);

            // Adicionar a nova mensagem ao arquivo
            print.println(texto);
            print.close();
        } catch (Exception e) {
            throw new Exception("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    public static List<String> ler() throws Exception {
        List<String> textos = new ArrayList<>();
        try {
            Scanner scan = new Scanner(ARQUIVO);
            while (scan.hasNextLine()) {
                textos.add(scan.nextLine());
            }
            scan.close();
            return textos;
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo: " + e.getMessage());
        }
    }
}
