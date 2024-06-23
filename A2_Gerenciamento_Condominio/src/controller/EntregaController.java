package controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IEntrega;
import model.Entrega;
import model.Residente;
import util.Log;
import util.Ser;

public class EntregaController implements IEntrega {

    private List<Entrega> entregas;

    public EntregaController(List<Entrega> entregas) {
        this.entregas = entregas;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS DE ENTREGAS");
        }
    }

    
    public void adicionarEntrega(Entrega entrega) throws Exception {
        this.entregas.add(entrega);

        Log.gravar("Entrega recebida para o residente " + entrega.getResidente().getNome());
        salvarDados();
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

    
    public List<Entrega> listarTodasEntregasNaoRetiradas() {
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

    
    public void salvarDados() throws Exception {
        Ser.salvarEntrega(entregas);;
    }

    private void carregarDados() throws Exception {
        entregas = Ser.lerEntregas();
    }
}
