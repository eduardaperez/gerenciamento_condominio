package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private static int proximoId = 1; // Variável estática para controlar o próximo ID disponível
    private int id;
    private Residente residente;
    private String area;
    private LocalDate dataReserva;

    // Construtor
    public Reserva(Residente residente, String area, LocalDate dataReserva) {
        this.id = gerarProximoId();
        this.residente = residente;
        this.area = area;
        this.dataReserva = dataReserva;
    }

    // Método estático sincronizado para gerar o próximo ID
    private synchronized static int gerarProximoId() {
        return proximoId++;
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
}
