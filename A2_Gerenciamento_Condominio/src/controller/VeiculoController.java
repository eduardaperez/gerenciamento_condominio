package controller;

import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IVeiculo;
import model.Veiculo;
import util.Log;
import util.Ser;

public class VeiculoController implements IVeiculo{
    private List<Veiculo> veiculos;

    public VeiculoController(List<Veiculo> veiculos) {
        this.veiculos = veiculos;

        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS DE VEICULOS");
        }
    }

 public void cadastrarVeiculo(Veiculo veiculo) throws Exception {
        veiculos.add(veiculo);

        Log.gravar("Veiculo registrado para o modelo " + veiculo.getModelo() + " e placa: " + veiculo.getPlaca());
        salvarDados();
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

    public void atualizarVeiculo(Veiculo veiculo, String placa, String tipo, String modelo) throws Exception {
        veiculo.setPlaca(placa);
        veiculo.setTipo(tipo);
        veiculo.setModelo(modelo);

        Log.gravar("Atualização do veiculo de placa" + veiculo.getPlaca());
        salvarDados();
    }
    
    public void removerVeiculo(String placa) throws Exception {
        veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
        Veiculo veiculo = obterVeiculo(placa);

        Log.gravar("Remoção do veiculo de placa " + veiculo.getPlaca());
        salvarDados();
    }

    public void salvarDados() throws Exception {
        Ser.salvarVeiculo(veiculos);
    }

    private void carregarDados() throws Exception {
        veiculos = Ser.lerVeiculos();
    }
}
