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

    public void adicionarResidente(Residente residente) {
        if (Validadores.ValidaCpf(residente.getCpf())) {
            residentes.add(residente);
            System.out.println("residente cadastrado com sucesso.");
        } else {
            System.out.println("CPF inválido. Cadastro não realizado.");
        }
    }

    public void adicionarVisitante(Visitante visitante) {
        if (Validadores.ValidaContato(visitante.getContato())) {
            visitantes.add(visitante);
            System.out.println("residente cadastrado com sucesso.");
        } else {
            System.out.println("CPF inválido. Cadastro não realizado.");
        }
    }

    // ... outros métodos
}