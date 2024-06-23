package controller.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.Acesso;
import model.Residente;
import model.Veiculo;
import model.Visitante;

public interface IAcesso {
    public abstract void registrarEntradaResidente(Residente residente, LocalDateTime entrada) throws Exception;
    public abstract void registrarEntradaVisitante(Visitante visitante, LocalDateTime entrada) throws Exception;
    public abstract void registrarEntradaVeiculo(Veiculo veiculo, LocalDateTime entrada) throws Exception;
    public abstract List<Acesso> listarAcessos();
    public abstract List<Acesso> listarAcessosDia(LocalDate dia);
    public abstract int gerarIdAcesso();

}
