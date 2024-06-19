package controller;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Acesso;
import model.Residente;
import model.Visitante;
//import model.Veiculo;

public class AcessoController {
    private List<Acesso> acessos;


    public AcessoController() {
        this.acessos = new ArrayList<>();
    }

    
    public void registrarEntradaResidente(Residente residente, LocalDateTime entrada) {
        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acessos.add(acesso);

    }

    public void registrarEntradaVisitante(Visitante visitante, LocalDateTime entrada) {

        Acesso acesso = new Acesso(gerarIdAcesso(), entrada);
        acessos.add(acesso);

    }


    
    public List<String> listarEntradas() {
        return acessos.stream()
            .map(acesso -> {
                String tipo = (acesso.getResidente() != null) ? "Residente" : "Visitante";
                String nome = (acesso.getResidente() != null) ? acesso.getResidente().getNome() : acesso.getVisitante().getNome();
                return "ID: " + acesso.getId() + ", Tipo: " + tipo + ", Nome: " + nome + ", Entrada: " + acesso.getEntrada();
            })
            .collect(Collectors.toList());
    }

    
    public List<String> listarEntradasPorDia(LocalDate dia) {
        return acessos.stream()
            .filter(acesso -> {
                LocalDate dataEntrada = acesso.getEntrada().toLocalDate();
                return dataEntrada.isEqual(dia);
            })
            .map(acesso -> {
                String tipo = (acesso.getResidente() != null) ? "Residente" : "Visitante";
                String nome = (acesso.getResidente() != null) ? acesso.getResidente().getNome() : acesso.getVisitante().getNome();
                return "ID: " + acesso.getId() + ", Tipo: " + tipo + ", Nome: " + nome + ", Entrada: " + acesso.getEntrada();
            })
            .collect(Collectors.toList());
    }

    public int gerarIdAcesso() {
        return acessos.stream().mapToInt(Acesso::getId).max().orElse(0) + 1;
    }
}
