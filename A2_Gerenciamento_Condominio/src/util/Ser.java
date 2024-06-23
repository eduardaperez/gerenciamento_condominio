package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.Acesso;
import model.Entrega;
import model.Reserva;
import model.Residente;
import model.Veiculo;
import model.Visitante;

public abstract class Ser {
    private static final File ARQUIVO_RESIDENTE = new File("obj/residente.ser");
    private static final File ARQUIVO_VISITANTE = new File("obj/visitante.ser");
    private static final File ARQUIVO_VEICULO = new File("obj/veiculo.ser");
    private static final File ARQUIVO_ACESSO = new File("obj/acesso.ser");
    private static final File ARQUIVO_RESERVA = new File("obj/reserva.ser");
    private static final File ARQUIVO_ENTREGA = new File("obj/entrega.ser");

    public static void salvarResidente(List<Residente> lista) throws Exception{
        try {
            ARQUIVO_RESIDENTE.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_RESIDENTE));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Residente> lerResidentes() throws Exception{
        try {
            if (ARQUIVO_RESIDENTE.exists() && ARQUIVO_RESIDENTE.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_RESIDENTE));

                return (List<Residente>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }

    public static void salvarVisitante(List<Visitante> lista) throws Exception{
        try {
            ARQUIVO_VISITANTE.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_VISITANTE));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Visitante> lerVisitantes() throws Exception{
        try {
            if (ARQUIVO_VISITANTE.exists() && ARQUIVO_VISITANTE.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_VISITANTE));

                return (List<Visitante>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }
    
    public static void salvarVeiculo(List<Veiculo> lista) throws Exception{
        try {
            ARQUIVO_VEICULO.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_VEICULO));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Veiculo> lerVeiculos() throws Exception{
        try {
            if (ARQUIVO_VEICULO.exists() && ARQUIVO_VEICULO.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_VEICULO));

                return (List<Veiculo>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }

    public static void salvarAcesso(List<Acesso> lista) throws Exception{
        try {
            ARQUIVO_ACESSO.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ACESSO));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Acesso> lerAcessos() throws Exception{
        try {
            if (ARQUIVO_ACESSO.exists() && ARQUIVO_ACESSO.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_ACESSO));

                return (List<Acesso>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }

    public static void salvarReserva(List<Reserva> lista) throws Exception{
        try {
            ARQUIVO_RESERVA.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_RESERVA));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Reserva> lerReservas() throws Exception{
        try {
            if (ARQUIVO_RESERVA.exists() && ARQUIVO_RESERVA.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_RESERVA));

                return (List<Reserva>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }

    public static void salvarEntrega(List<Entrega> lista) throws Exception{
        try {
            ARQUIVO_ENTREGA.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ENTREGA));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo");
        }
    }

    public static List<Entrega> lerEntregas() throws Exception{
        try {
            if (ARQUIVO_ENTREGA.exists() && ARQUIVO_ENTREGA.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO_ENTREGA));

                return (List<Entrega>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo");
        }
    }
}
