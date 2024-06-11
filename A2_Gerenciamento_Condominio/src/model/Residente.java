package model;

public class Residente {
    private int id; // Identificador único do residente
    private String nome; // Nome do residente
    private String endereco; // Endereço do residente no condomínio
    private String contato; // Informações de contato do residente
    private String documento; // Documento de identificação do residente (ex: RG, CPF)
    //private List<Veiculo> veiculos; // Lista de veículos pertencentes ao residente

    public Residente(int id, String nome, String endereco, String contato, String documento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.documento = documento;
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
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    
}

