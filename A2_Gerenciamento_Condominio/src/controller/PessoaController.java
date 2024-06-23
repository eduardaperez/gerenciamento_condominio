// src/controller/PessoaController.java
package controller;

import model.Residente;
import model.Visitante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IPessoa;

public class PessoaController implements IPessoa{
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
                         .filter(r -> r.getCpf().equals(cpf))
                         .findFirst()
                         .orElse(null);
    }

    public Residente obterResidentePorBlocoEApartamento(int bloco, int apartamento) {
        return residentes.stream()
                         .filter(r -> r.getBloco() == bloco && r.getApartamento() == apartamento)
                         .findFirst()
                         .orElse(null);
    }

    public List<Residente> listarResidentes() {
        return new ArrayList<>(residentes);
    }

    public void atualizarResidente(Residente residente, String nome, String contato, int bloco, int apto, LocalDate dtaNascimento) {
        residente.setNome(nome);
        residente.setContato(contato);
        residente.setBloco(bloco);
        residente.setApartamento(apto);
        residente.setDataNascimento(dtaNascimento);
    }

    public void removerResidente(String cpf) {
        residentes.removeIf(residente -> residente.getCpf().equals(cpf));
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

    public void atualizarVisitante(Visitante visitante, String nome, String telefone) {
        visitante.setNome(nome);
        visitante.setContato(telefone);
    }

    public void removerVisitante(int id) {
        visitantes.removeIf(visitante -> visitante.getId() == id);
    }

    public Visitante obterVisitantePorNomeETelefone(String nome, String telefone) {
        return visitantes.stream()
                         .filter(v -> v.getNome().equalsIgnoreCase(nome) && v.getContato().equals(telefone))
                         .findFirst()
                         .orElse(null);    
    }

    public Visitante obterVisitatePorTelefone(String telefone) {
        return visitantes.stream()
                         .filter(v -> v.getContato().equals(telefone))
                         .findFirst()
                         .orElse(null);   
    }

    public int gerarIdVisitante() {
        return visitantes.stream().mapToInt(Visitante::getId).max().orElse(0) + 1;
    }
}