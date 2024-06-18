// src/controller/PessoaController.java
package controller;

import model.Residente;
import model.Visitante;
import util.Validadores;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    private List<Residente> residentes = new ArrayList<>();
    private List<Visitante> visitantes = new ArrayList<>();


    //Métodos Residentes
    public void cadastrarResidente(Residente residente) {
        if (Validadores.ValidaCpf(residente.getCpf())) {
            residentes.add(residente);
            System.out.println("residente cadastrado com sucesso.");
        } else {
            System.out.println("CPF inválido. Cadastro não realizado.");
        }
    }

    public Residente obterResidente(String cpf) {
        for (Residente residente : residentes) {
            if (residente.getCpf() == cpf) {
                return residente;
            }
        }
        return null;
    }

    public Residente buscarResidentePorBlocoEApartamento(int bloco, int apartamento) {
        for (Residente residente : residentes) {
            if (residente.getBloco() == bloco && residente.getApartamento() == apartamento) {
                return residente;
            }
        }
        return null;
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
    public void cadastrarVisitante(Visitante visitante) {
        if (Validadores.ValidaContato(visitante.getContato())) {
            visitantes.add(visitante);
            System.out.println("Residente cadastrado com sucesso.");
        } else {
            System.out.println("CPF inválido. Cadastro não realizado.");
        }
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
}