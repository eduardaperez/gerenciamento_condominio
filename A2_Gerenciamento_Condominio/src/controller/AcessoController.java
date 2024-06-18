package controller;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Acesso;
import model.Residente;
import model.Visitante;

public class AcessoController {
    private List<Acesso> acessos;
    private List<Residente> residentes;
    private List<Visitante> visitantes;
    private int idCounter;

    public AcessoController(List<Residente> residentes, List<Visitante> visitantes) {
        this.acessos = new ArrayList<>();
        this.residentes = residentes;
        this.visitantes = visitantes;
        this.idCounter = 1;
    }

    
    public void registrarEntrada(Residente residente, Visitante visitante, LocalDateTime entrada) {
        if ((residente != null && residentes.contains(residente)) || (visitante != null && visitantes.contains(visitante))) {
            Acesso acesso = new Acesso(idCounter++, residente, visitante, entrada, null);
            acessos.add(acesso);
        } else {
            throw new IllegalArgumentException("Residente ou visitante não está cadastrado.");
        }
    }

    
    public void registrarSaida(int id, LocalDateTime saida) {
        for (Acesso acesso : acessos) {
            if (acesso.getId() == id && acesso.getSaida() == null) {
                acesso.setSaida(saida);
                return;
            }
        }
        throw new IllegalArgumentException("Acesso não encontrado ou já registrado a saída.");
    }

    
    public List<String> listarEntradasSaidas() {
        return acessos.stream()
            .map(acesso -> {
                String tipo = (acesso.getResidente() != null) ? "Residente" : "Visitante";
                String nome = (acesso.getResidente() != null) ? acesso.getResidente().getNome() : acesso.getVisitante().getNome();
                return "ID: " + acesso.getId() + ", Tipo: " + tipo + ", Nome: " + nome + ", Entrada: " + acesso.getEntrada() + ", Saída: " + acesso.getSaida();
            })
            .collect(Collectors.toList());
    }

    
    public List<String> listarEntradasSaidasPorDia(LocalDate dia) {
        return acessos.stream()
            .filter(acesso -> {
                LocalDate dataEntrada = acesso.getEntrada().toLocalDate();
                LocalDate dataSaida = (acesso.getSaida() != null) ? acesso.getSaida().toLocalDate() : null;
                return dataEntrada.isEqual(dia) || (dataSaida != null && dataSaida.isEqual(dia));
            })
            .map(acesso -> {
                String tipo = (acesso.getResidente() != null) ? "Residente" : "Visitante";
                String nome = (acesso.getResidente() != null) ? acesso.getResidente().getNome() : acesso.getVisitante().getNome();
                return "ID: " + acesso.getId() + ", Tipo: " + tipo + ", Nome: " + nome + ", Entrada: " + acesso.getEntrada() + ", Saída: " + acesso.getSaida();
            })
            .collect(Collectors.toList());
    }
}
