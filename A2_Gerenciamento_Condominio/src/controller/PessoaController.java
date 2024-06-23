// src/controller/PessoaController.java
package controller;

import model.Residente;
import model.Visitante;
import util.Log;
import util.Ser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IPessoa;

public class PessoaController implements IPessoa{
    private List<Residente> residentes;
    private List<Visitante> visitantes;

    public PessoaController(List<Residente> residentes, List<Visitante> visitantes) {
        this.residentes = residentes;
        this.visitantes = visitantes;

        try {
            carregarDadosResidente();
            carregarDadosVisitante();
        } catch (Exception e) {
            System.err.println("ERRO AO CARREGAR DADOS DE PESSOAS");
        }
    }

    //Métodos Residentes
    public boolean cadastrarResidente(Residente residente) throws Exception {
        if (obterResidente(residente.getCpf()) != null) {
            String mensagem = "Residente já está cadastrado com este CPF.";
            System.out.println(mensagem);
        }

        residentes.add(residente);

        Log.gravar("Residente cadastrado de nome " + residente.getNome() + " e telefone: " + residente.getContato());
        salvarDadosResidente();

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

    public void atualizarResidente(Residente residente, String nome, String contato, int bloco, int apto, LocalDate dtaNascimento) throws Exception {
        residente.setNome(nome);
        residente.setContato(contato);
        residente.setBloco(bloco);
        residente.setApartamento(apto);
        residente.setDataNascimento(dtaNascimento);

        Log.gravar("Atualização para o residente de nome " + residente.getNome() + " e telefone: " + residente.getContato());
        salvarDadosResidente();
    }

    public void removerResidente(String cpf) throws Exception {
        residentes.removeIf(residente -> residente.getCpf().equals(cpf));
        Residente residente = obterResidente(cpf);

        Log.gravar("Exclusão para o residente de nome " + residente.getNome() + " e telefone: " + residente.getContato());
        salvarDadosResidente();
    }

    //Métodos Visitantes
    public boolean cadastrarVisitante(Visitante visitante) throws Exception {
        if (obterVisitantePorNomeETelefone(visitante.getNome(), visitante.getContato()) != null) {
            System.out.println("Ja existe um visitante cadastrado com este nome e telefone!");
            return false;
        }

        visitantes.add(visitante);

        Log.gravar("Visitante cadastrado de nome " + visitante.getNome() + " e telefone: " + visitante.getContato());
        salvarDadosVisitante();

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

    public void atualizarVisitante(Visitante visitante, String nome, String telefone) throws Exception {
        visitante.setNome(nome);
        visitante.setContato(telefone);

        
        Log.gravar("Atualização para o visitante de nome " + visitante.getNome() + " e telefone: " + visitante.getContato());
        salvarDadosVisitante();
    }

    public void removerVisitante(int id) throws Exception {
        visitantes.removeIf(visitante -> visitante.getId() == id);
        Visitante visitante = obterVisitante(id);

        Log.gravar("Atualização para o visitante de nome " + visitante.getNome() + " e telefone: " + visitante.getContato());
        salvarDadosVisitante();
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

    public void salvarDadosResidente() throws Exception {
        Ser.salvarResidente(residentes);
    }

    private void carregarDadosResidente() throws Exception {
        residentes = Ser.lerResidentes();
    }

    public void salvarDadosVisitante() throws Exception {
        Ser.salvarVisitante(visitantes);
    }

    private void carregarDadosVisitante() throws Exception {
        visitantes = Ser.lerVisitantes();
    }
}