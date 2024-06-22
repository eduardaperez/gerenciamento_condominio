package controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.Reserva;

public interface IReserva {
    public abstract void adicionarReserva(Reserva reserva);
    public abstract List<String> listarDatasOcupadasPorArea(String area);
    public abstract boolean pesquisarDisponibilidadePorData(LocalDate data, String area);
    public abstract void cancelarReserva(int id);
    public abstract List<Reserva> buscarReservasPorNomeCpf(String cpfResidente);
    public abstract List<String> listarTodasReservas();
    public abstract int gerarIdReserva();

}
