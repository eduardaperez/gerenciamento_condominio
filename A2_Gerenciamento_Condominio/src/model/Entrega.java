package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entrega implements Serializable {
    private int id; 
    private Residente residente; 
    private String descricao; 
    private LocalDateTime dataRecebimento; 
    private boolean retirada; 
    
    public Entrega(int id, Residente residente, String descricao, LocalDateTime dataRecebimento) {
        this.id = id;
        this.residente = residente;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.retirada = false;
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

