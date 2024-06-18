package model;

import java.time.LocalDateTime;

public class Acesso {
    private int id;
    private Residente residente;
    private Visitante visitante;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Acesso(int id, Residente residente, Visitante visitante, LocalDateTime entrada, LocalDateTime saida) {
        this.id = id;
        this.residente = residente;
        this.visitante = visitante;
        this.entrada = entrada;
        this.saida = saida;
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

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }
}
