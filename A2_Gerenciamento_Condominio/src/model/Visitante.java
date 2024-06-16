package model;

public class Visitante extends Pessoa {
    private Residente residente;


    public Visitante(int id, String nome, String contato, Residente residente) {
        super(id, nome, contato);
        this.residente = residente;
    }


    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }
}

