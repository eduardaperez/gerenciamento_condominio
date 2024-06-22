package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import model.Veiculo;

public class VeiculoController {
    private List<Veiculo> veiculos;

    public VeiculoController() {
        this.veiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo obterVeiculo(String placa) {
        return veiculos.stream()
                       .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                       .findFirst()
                       .orElse(null);
    }

    public List<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        veiculo.setTipo(null);
        veiculo.setPlaca(null);
        veiculo.setModelo(null);
    }

    public void removerVeiculo(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getPlaca() == placa);
    }
}
