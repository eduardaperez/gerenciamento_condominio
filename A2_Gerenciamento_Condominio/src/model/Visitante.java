package model;

public class Visitante {
    private int id; // Identificador único do visitante
    private String nome; // Nome do visitante
    private String documento; // Documento de identificação do visitante (ex: RG)
    private String contato; // Informações de contato do visitante
    
    public Visitante(int id, String nome, String documento, String contato) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    
}

