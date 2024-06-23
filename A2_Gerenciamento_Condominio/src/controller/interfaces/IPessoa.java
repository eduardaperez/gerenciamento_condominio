package controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.Residente;
import model.Visitante;

public interface IPessoa {
    public abstract boolean cadastrarResidente(Residente residente);
    public abstract boolean cadastrarVisitante(Visitante visitante);
    public abstract Residente obterResidente(String cpf);
    public abstract Visitante obterVisitante(int id);
    public abstract Residente obterResidentePorBlocoEApartamento(int bloco, int apartamento);
    public abstract Visitante obterVisitantePorNomeETelefone(String nome, String telefone);
    public abstract List<Residente> listarResidentes();
    public abstract List<Visitante> listarVisitantes();
    public abstract void atualizarResidente(Residente residente, String nome, String contato, int bloco, int apto, LocalDate dtaNascimento);
    public abstract void atualizarVisitante(Visitante visitante, String nome, String telefone);
    public abstract void removerResidente(String cpf);
    public abstract void removerVisitante(int id);
    public abstract int gerarIdVisitante();

}
