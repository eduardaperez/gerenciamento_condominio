package model;

import java.time.LocalDateTime;

public class Entrega {
    private int id; // Identificador único da entrega
    private Residente residente; // Residente associado à entrega
    private String descricao; // Descrição da entrega (ex: pacote, carta)
    private LocalDateTime dataRecebimento; // Data e hora em que a entrega foi recebida pela portaria
    private boolean retirada; // Indica se a entrega já foi retirada pelo residente
    
    public Entrega(int id, Residente residente, String descricao, LocalDateTime dataRecebimento, boolean retirada) {
        this.id = id;
        this.residente = residente;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.retirada = retirada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public boolean isRetirada() {
        return retirada;
    }

    public void setRetirada(boolean retirada) {
        this.retirada = retirada;
    }

    
}

