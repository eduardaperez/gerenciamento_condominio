package controller.interfaces;

import java.util.List;

import model.Residente;
import model.Visitante;

public interface IPessoa {
    public abstract boolean cadastrarResidente(Residente residente);
    public abstract boolean cadastrarVisitante(Visitante visitante);
    public abstract Residente obterResidente(String cpf);
    public abstract Visitante obterVisitante(int id);
    public abstract Residente buscarResidentePorBlocoEApartamento(int bloco, int apartamento); //obter
    public abstract Visitante obterVisitantePorNomeETelefone(String nome, String telefone);
    public abstract List<Residente> listarResidentes();
    public abstract List<Visitante> listarVisitantes();
    public abstract void atualizarResidente(Residente residente);
    public abstract void atualizarVisitante(Visitante visitante);
    public abstract void removerResidente(String cpf);
    public abstract void removerVisitante(int id);
    public abstract int gerarIdVisitante();

}
