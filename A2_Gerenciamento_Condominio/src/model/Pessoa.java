package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    private String nome;
    private String contato;

    
    public Pessoa(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }

}
