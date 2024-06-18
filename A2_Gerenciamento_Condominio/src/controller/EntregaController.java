package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Entrega;
import model.Residente;

public class EntregaController {

    private List<Entrega> entregas;

    public EntregaController() {
        this.entregas = new ArrayList<>();
    }

    // Método para adicionar uma nova entrega
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

    
    public List<String> listarEntregasRealizadas() {
        return this.entregas.stream()
            .filter(Entrega::isRetirada)
            .map(entrega -> "Data: " + entrega.getDataRecebimento() + ", CPF: " + entrega.getResidente().getClass())
            .collect(Collectors.toList());
    }
}
