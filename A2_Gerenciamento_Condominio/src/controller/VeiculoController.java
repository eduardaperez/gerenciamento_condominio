package controller;

import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IVeiculo;
import model.Veiculo;

public class VeiculoController implements IVeiculo{
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

    public void atualizarVeiculo(Veiculo veiculo, String placa, String tipo, String modelo) {
        veiculo.setPlaca(placa);
        veiculo.setTipo(tipo);
        veiculo.setModelo(modelo);
    }

    public void removerVeiculo(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
    }
}
