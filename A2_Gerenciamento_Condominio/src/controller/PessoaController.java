// src/controller/PessoaController.java
package controller;

import model.Pessoa;
import model.Residente;
import model.Visitante;

import util.Validadores;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PessoaController {
    private List<Residente> residentes = new ArrayList<>();
    private List<Visitante> visitantes = new ArrayList<>();


    //Métodos Residentes
    public boolean cadastrarResidente(Residente residente) {
        if (obterResidente(residente.getCpf()) != null) {
            System.out.println("Residente já está cadastrado com este CPF.");
            return false;
        }

        residentes.add(residente);
        return true;
    }

    public Residente obterResidente(String cpf) {
        return residentes.stream()
                         .filter(r -> r.getCpf().equalsIgnoreCase(cpf))
                         .findFirst()
                         .orElse(null);
    }
     
    

    public Residente buscarResidentePorBlocoEApartamento(int bloco, int apartamento) {
        return residentes.stream()
                         .filter(r -> r.getBloco() == bloco && r.getApartamento() == apartamento)
                         .findFirst()
                         .orElse(null);
    }

    public List<Residente> listarResidentes() {
        return new ArrayList<>(residentes);
    }

    public void atualizarResidente(Residente residente) {
        residente.setCpf(null);
        residente.setNome(null);
        residente.setContato(null);
        residente.setDataNascimento(null);
        residente.setVeiculos(null);
    }

    public void removerResidente(String cpf) {
        residentes.removeIf(residente -> residente.getCpf() == cpf);
    }

    //Métodos Visitantes
    public boolean cadastrarVisitante(Visitante visitante) {
        if (obterVisitantePorNomeETelefone(visitante.getNome(), visitante.getContato()) != null) {
            System.out.println("Ja existe um visitante cadastrado com este nome e telefone!");
            return false;
        }

        visitantes.add(visitante);
        return true;
    }

    public Visitante obterVisitante(int id) {
        for (Visitante visitante : visitantes) {
            if (visitante.getId() == id) {
                return visitante;
            }
        }
        return null;
    }

    public List<Visitante> listarVisitantes() {
        return new ArrayList<>(visitantes);
    }

    public void atualizarVisitante(Visitante visitante) {
        visitante.setNome(null);
        visitante.setContato(null);
        //visitante.setVeiculos(null);
    }

    public void removerVisitante(int id) {
        visitantes.removeIf(visitante -> visitante.getId() == id);
    }

    public Visitante obterVisitantePorNomeETelefone(String nome, String telefone) {
        return visitantes.stream()
                         .filter(v -> v.getNome().equalsIgnoreCase(nome) && v.getContato().equalsIgnoreCase(telefone))
                         .findFirst()
                         .orElse(null);    
    }

    public int gerarIdVisitante() {
        return visitantes.stream().mapToInt(Visitante::getId).max().orElse(0) + 1;
    }
}