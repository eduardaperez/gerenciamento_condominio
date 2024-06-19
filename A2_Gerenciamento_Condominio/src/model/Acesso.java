package model;

import java.time.LocalDateTime;

public class Acesso {
    private int id;
    private Residente residente;
    private Visitante visitante;
    private LocalDateTime entrada;

    public Acesso(int id, LocalDateTime entrada) {
        this.id = id;
        this.residente = null;
        this.visitante = null;
        this.entrada = entrada;
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
}
