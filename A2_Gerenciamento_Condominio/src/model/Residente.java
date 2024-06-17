package model;

import java.util.List;

import javax.xml.crypto.Data;

public class Residente extends Pessoa {
    private String cpf;
    private int bloco, apartamento, vaga; // Endereço do residente no condomínio
    private Data dataNascimento;
    private List<Veiculo> veiculos; // Lista de veículos pertencentes ao residente

    
    public Residente(int id, String nome, String contato,String cpf, int bloco, int apartamento, int vaga, Data dataNascimento,
            List<Veiculo> veiculos) {
        super(id, nome, contato);
        this.cpf = cpf;
        this.bloco = bloco;
        this.apartamento = apartamento;
        this.vaga = vaga;
        this.dataNascimento = dataNascimento;
        this.veiculos = veiculos;
    }
    

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public int getBloco() {
        return bloco;
    }
    public void setBloco(int bloco) {
        this.bloco = bloco;
    }
    public int getApartamento() {
        return apartamento;
    }
    public void setApartamento(int apartamento) {
        this.apartamento = apartamento;
    }
    public int getVaga() {
        return vaga;
    }
    public void setVaga(int vaga) {
        this.vaga = vaga;
    }
    public Data getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    
}

