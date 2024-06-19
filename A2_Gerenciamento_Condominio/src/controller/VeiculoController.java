package controller;

import java.util.ArrayList;
import java.util.List;

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
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
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
