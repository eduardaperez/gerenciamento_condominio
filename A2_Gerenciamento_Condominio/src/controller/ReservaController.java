package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.Reserva;

public class ReservaController {
    private List<Reserva> reservas;

    public ReservaController() {
        this.reservas = new ArrayList<>();
    }

    public void adicionarReserva(Reserva reserva) {
        boolean disponivel = reservas.stream()
            .noneMatch(r -> r.getArea().equals(reserva.getArea()) && r.getDataReserva().isEqual(reserva.getDataReserva()));

        if (disponivel) {
            reservas.add(reserva);
        } else {
            throw new IllegalArgumentException("A área já está reservada para esta data e hora.");
        }
    }

   
    public List<String> listarReservas() {
        return reservas.stream()
            .sorted(Comparator.comparing(Reserva::getDataReserva))
            .map(reserva -> "Residente: " + reserva.getResidente().getNome() + ", Área: " + reserva.getArea() + ", Data: " + reserva.getDataReserva())
            .collect(Collectors.toList());
    }

 
    public boolean pesquisarPorData(LocalDateTime data, String area) {
        return reservas.stream()
            .noneMatch(reserva -> reserva.getArea().equals(area) && reserva.getDataReserva().isEqual(data));
    }

    
    public void cancelarReserva(int id) {
        reservas.removeIf(reserva -> reserva.getId() == id);
    }
}
