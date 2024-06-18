package model;

public class Veiculo {
    private String placa, modelo, tipo;
    private Residente residente;

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, String tipo, Residente residente) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.residente = residente;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Residente getResidente() {
        return residente;
    }
    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    

}
