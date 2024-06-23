package controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IReserva;
import model.Reserva;
import util.Log;
import util.Ser;

public class ReservaController implements IReserva{
    private List<Reserva> reservas;

    public ReservaController(List<Reserva> reservas) {
        this.reservas = reservas;

        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS DE RESERVAS");
        }
    }
    

    public void adicionarReserva(Reserva reserva) throws Exception {
        boolean disponivel = reservas.stream()
                                     .noneMatch(r -> r.getArea().equals(reserva.getArea()) && r.getDataReserva().isEqual(reserva.getDataReserva()));

        if (disponivel) {
            reservas.add(reserva);
        } else {
            throw new IllegalArgumentException("A área já está reservada para esta data.");
        }

        Log.gravar("Reserva realizada para a area " + reserva.getArea());
        salvarDados();
    }

    public List<String> listarDatasOcupadasPorArea(String area) {
        return reservas.stream()
                       .filter(r -> r.getArea().equals(area))
                       .sorted(Comparator.comparing(Reserva::getDataReserva))
                       .map(reserva -> reserva.getDataReserva().toString())
                       .collect(Collectors.toList());
    }

    public boolean pesquisarDisponibilidadePorData(LocalDate data, String area) {
        return reservas.stream()
                       .noneMatch(reserva -> reserva.getArea().equals(area) && reserva.getDataReserva().isEqual(data));
    }

    public void cancelarReserva(int id) throws Exception {
        reservas.removeIf(reserva -> reserva.getId() == id);

        Log.gravar("Reserva cancelada");
        salvarDados();
    }

    public List<Reserva> buscarReservasPorNomeCpf(String cpfResidente) {
        return reservas.stream()
                       .filter(reserva -> reserva.getResidente().getCpf().equals(cpfResidente))
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

    
    public void salvarDados() throws Exception {
        Ser.salvarReserva(reservas);
    }

    private void carregarDados() throws Exception {
        reservas = Ser.lerReservas();
    }
}
