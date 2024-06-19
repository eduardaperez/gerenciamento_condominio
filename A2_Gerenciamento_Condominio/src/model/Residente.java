package model;

import java.time.LocalDate;
import java.util.List;

public class Residente extends Pessoa {
    private int bloco, apartamento;
    private LocalDate dataNascimento;
    private String cpf;
    private List<Veiculo> veiculos; 

    
    public Residente(String nome, String contato) {
        super(nome, contato);
    }

    public Residente(String nome, String contato, String cpf, int bloco, int apartamento, LocalDate dataNascimento,
            List<Veiculo> veiculos) {
        super(nome, contato);
        this.cpf = cpf;
        this.bloco = bloco;
        this.apartamento = apartamento;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
    
}

