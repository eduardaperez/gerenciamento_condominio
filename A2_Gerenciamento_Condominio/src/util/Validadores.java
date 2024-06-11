package util;

public abstract class Validadores {

    public static boolean ValidaCpf(String cpf)
    {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        return cpf.length() == 11;
    }

    public static boolean ValidaPlaca(String placa)
    {
        placa = placa.trim().replace("-", "");

        // Expressões regulares para os dois formatos de placas
        String regexAntigo = "^[A-Z]{3}-[0-9]{4}$";       // Formato antigo
        String regexMercosul = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$"; // Formato Mercosul

        // Verifica se a placa corresponde a algum dos formatos
        return placa.matches(regexAntigo) || placa.matches(regexMercosul);
    }
}
