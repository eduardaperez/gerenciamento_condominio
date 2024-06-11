package model;

import java.time.LocalDateTime;

public class Reserva {
    private int id; // Identificador único da reserva
    private Residente residente; // Residente que fez a reserva
    private String area; // Área reservada (ex: salão de festas, churrasqueira)
    private LocalDateTime dataReserva; // Data e hora da reserva

    public Reserva(int id, Residente residente, String area, LocalDateTime dataReserva) {
        this.id = id;
        this.residente = residente;
        this.area = area;
        this.dataReserva = dataReserva;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    
}

