package controller;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Acesso;
import model.Residente;
import model.Veiculo;
import model.Visitante;

public class AcessoController {
    private List<Acesso> acessos;


    public AcessoController() {
        this.acessos = new ArrayList<>();
    }

    
    public void registrarEntradaResidente(Residente residente, LocalDateTime entrada) {
        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setResidente(residente);
        acessos.add(acesso);

    }

    public void registrarEntradaVisitante(Visitante visitante, LocalDateTime entrada) {

        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setVisitante(visitante);
        acessos.add(acesso);

    }

    public void registrarEntradaVeiculo(Veiculo veiculo, LocalDateTime entrada) {

        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acesso.setVeiculo(veiculo);
        acessos.add(acesso);

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
}