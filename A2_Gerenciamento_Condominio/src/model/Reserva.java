package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable{
    private int id;
    private Residente residente;
    private String area;
    private LocalDate dataReserva;

    // Construtor
    public Reserva(int id, Residente residente, String area, LocalDate dataReserva) {
        this.id = id;
        this.residente = residente;
        this.area = area;
        this.dataReserva = dataReserva;
    }


    // Getters e Setters
    public int getId() {
        return id;
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

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }


    @Override
    public String toString() {
        return "Reserva [id=" + id + ", residente=" + residente + ", area=" + area + ", dataReserva=" + dataReserva
                + "]";
    }

    
}
