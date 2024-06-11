package controller;

import java.util.ArrayList;
import java.util.List;
import model.Residente;

public class ResidenteController {
    private List<Residente> residentes;

    public ResidenteController() {
        this.residentes = new ArrayList<>();
    }

    public void cadastrarResidente(Residente residente) {
        residentes.add(residente);
    }

    public Residente obterResidente(int id) {
        for (Residente residente : residentes) {
            if (residente.getId() == id) {
                return residente;
            }
        }
        return null;
    }

    public List<Residente> listarResidentes() {
        return new ArrayList<>(residentes);
    }

    public void atualizarResidente(Residente residente) {
        // Implementar atualização se necessário
    }

    public void removerResidente(int id) {
        residentes.removeIf(residente -> residente.getId() == id);
    }
}
