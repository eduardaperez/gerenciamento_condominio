package controller.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import model.Entrega;

public interface IEntrega {
    public abstract void adicionarEntrega(Entrega entrega);
    public abstract List<Entrega> listarEntregasPorCpf(int cpf);
    public abstract void registrarRetirada(int id, LocalDateTime dataRetirada);
    public abstract List<Entrega> listarTodasEntregasNaoRetiradas();
    public abstract List<Entrega> listarEntregasNaoRetiradasPorResidente(String cpf);
    public abstract List<Entrega> listarEntregasRealizadas();
    public abstract int gerarIdEntrega();

}
