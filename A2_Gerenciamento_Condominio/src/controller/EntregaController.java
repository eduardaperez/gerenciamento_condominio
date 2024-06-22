package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Entrega;

public class EntregaController {

    private List<Entrega> entregas;

    public EntregaController() {
        this.entregas = new ArrayList<>();
    }

    
    public void adicionarEntrega(Entrega entrega) {
        this.entregas.add(entrega);
    }


    public List<Entrega> listarEntregasPorCpf(int cpf) {
        return this.entregas.stream()
            .filter(entrega -> entrega.getResidente().getClass().equals(cpf))
            .collect(Collectors.toList());
    }

    
    public void registrarRetirada(int id, LocalDateTime dataRetirada) {
        for (Entrega entrega : entregas) {
            if (entrega.getId() == id) {
                entrega.setRetirada(true);
                entrega.setDataRecebimento(dataRetirada);
                break;
            }
        }
    }

    
    public List<Entrega> listarEntregasNaoRetiradas() {
        return this.entregas.stream()
            .filter(entrega -> !entrega.isRetirada())
            .collect(Collectors.toList());
    }

    public List<Entrega> listarEntregasNaoRetiradasPorResidente(String cpf) {
        return this.entregas.stream()
            .filter(entrega -> !entrega.isRetirada() && entrega.getResidente().getCpf().equals(cpf))
            .collect(Collectors.toList());
    }

    
    public List<Entrega> listarEntregasRealizadas() {
        return this.entregas.stream()
            .filter(Entrega::isRetirada)
            .collect(Collectors.toList());
    }

    
    public int gerarIdEntrega() {
        return entregas.stream().mapToInt(Entrega::getId).max().orElse(0) + 1;
    }
}
