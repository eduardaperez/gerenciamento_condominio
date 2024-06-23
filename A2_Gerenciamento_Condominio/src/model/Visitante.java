package model;

public class Visitante extends Pessoa {
    private int id;
    private Residente residente;

    public Visitante(String nome, String contato) {
        super(nome, contato);
    }


    public Visitante(int id, String nome, String contato, Residente residente) {
        super(nome, contato);
        this.id = id;
        this.residente = residente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }


    @Override
    public String toString() {
        return "Visitante [id=" + id + ", residente=" + residente + "]";
    }
}

