package controller;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IAcesso;
import model.Acesso;
import model.Residente;
import model.Veiculo;
import model.Visitante;
import util.Log;
import util.Ser;

public class AcessoController implements IAcesso {
    private List<Acesso> acessos;

    public AcessoController(List<Acesso> acessos) {
        this.acessos = acessos;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS DE ACESSOS");
        }
    }

    public void registrarEntradaResidente(Residente residente, LocalDateTime entrada) throws Exception {
        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setResidente(residente);
        acessos.add(acesso);

        Log.gravar("Entrada registrada para o residente " + residente.getNome());
        salvarDados();
    }

    public void registrarEntradaVisitante(Visitante visitante, LocalDateTime entrada) throws Exception {

        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setVisitante(visitante);
        acessos.add(acesso);

        Log.gravar("Entrada registrada para o visitante " + visitante.getNome());
        salvarDados();
    }

    public void registrarEntradaVeiculo(Veiculo veiculo, LocalDateTime entrada) throws Exception {

        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setVeiculo(veiculo);
        acessos.add(acesso);

        Log.gravar("Entrada registrada do veiculo " + veiculo.getModelo() + " placa: " + veiculo.getPlaca());
        salvarDados();
    }
    
    public List<Acesso> listarAcessos() {
        return new ArrayList<>(acessos);
    }

    
    public List<Acesso> listarAcessosDia(LocalDate dia) {
        return acessos.stream()
            .filter(acesso -> acesso.getEntrada().toLocalDate().isEqual(dia))
            .collect(Collectors.toList());
    }

    public int gerarIdAcesso() {
        return acessos.stream().mapToInt(Acesso::getId).max().orElse(0) + 1;
    }
    
    public void salvarDados() throws Exception {
        Ser.salvarAcesso(acessos);
    }

    private void carregarDados() throws Exception {
        acessos = Ser.lerAcessos();
    }
}