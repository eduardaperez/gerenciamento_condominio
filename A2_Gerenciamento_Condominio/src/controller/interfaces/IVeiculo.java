package controller.interfaces;

import java.util.List;

import model.Veiculo;

public interface IVeiculo {
    public abstract void cadastrarVeiculo(Veiculo veiculo) throws Exception;
    public abstract Veiculo obterVeiculo(String placa);
    public abstract List<Veiculo> listarVeiculos();
    public abstract void atualizarVeiculo(Veiculo veiculo, String placa, String tipo, String modelo) throws Exception;
    public abstract void removerVeiculo(String placa) throws Exception;

}
