package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IReserva;
import model.Reserva;
import model.Visitante;

public class ReservaController implements IReserva{
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
            throw new IllegalArgumentException("A área já está reservada para esta data.");
        }
    }

    public List<String> listarDatasOcupadasPorArea(String area) {
        return reservas.stream()
                       .filter(r -> r.getArea().equalsIgnoreCase(area))
                       .sorted(Comparator.comparing(Reserva::getDataReserva))
                       .map(reserva -> reserva.getDataReserva().toString())
                       .collect(Collectors.toList());
    }

    public boolean pesquisarDisponibilidadePorData(LocalDate data, String area) {
        return reservas.stream()
                       .noneMatch(reserva -> reserva.getArea().equals(area) && reserva.getDataReserva().isEqual(data));
    }

    public void cancelarReserva(int id) {
        reservas.removeIf(reserva -> reserva.getId() == id);
    }

    public List<Reserva> buscarReservasPorNomeCpf(String cpfResidente) {
        return reservas.stream()
                       .filter(reserva -> reserva.getResidente().getCpf().equalsIgnoreCase(cpfResidente))
                       .collect(Collectors.toList());
    }
    
    public List<String> listarTodasReservas() {
        return reservas.stream()
                .sorted(Comparator.comparing(Reserva::getDataReserva))
                .map(reserva -> "Residente: " + reserva.getResidente().getNome() +
                                ", Área: " + reserva.getArea() +
                                ", Data : " + reserva.getDataReserva().toString())
                .collect(Collectors.toList());
    }

    public int gerarIdReserva(){
        return reservas.stream().mapToInt(Reserva::getId).max().orElse(0) + 1;
    }
}
