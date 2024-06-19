package view;

import java.util.List;
import java.util.Scanner;

import controller.PessoaController;
import controller.VeiculoController;
import model.Residente;
import model.Veiculo;
import util.Validadores;

public class VeiculoView {

    // Cadastro com vinculação a um proprietario, sendo ele um residente
    public static void cadastrarVeiculo(VeiculoController vController, PessoaController pController, Scanner scan) {

        System.out.println("\n--- Cadastro de Veículo ---");

        System.out.print("Placa: ");
        String placa = scan.nextLine().trim().toUpperCase();

        if (!Validadores.ValidaPlaca(placa)) {
            System.out.println("Placa no formato inválido.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scan.nextLine().trim();

        System.out.print("Tipo: ");
        String tipo = scan.nextLine().trim();

        if (placa.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        System.out.print("Cadastro para [1 - Residente, 2 - Visitante]: ");
        int tipoCadastro = scan.nextInt();
        scan.nextLine(); 

        Residente residente = null;
        if (tipoCadastro == 1) {

            System.out.print("CPF do Residente associado: ");
            String residenteCpf = scan.nextLine().trim();

            if (!Validadores.ValidaCpf(residenteCpf)) {
                System.out.println("CPF no formato inválido.");

            return;
            }

            residente = pController.obterResidente(residenteCpf);
            if (residente == null) {
                System.out.println("Residente não encontrado! \nPor favor, registre o residente.");
                return;
            }
        }

        try {
            Veiculo veiculo = new Veiculo(placa, modelo, tipo, residente);
            vController.cadastrarVeiculo(veiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Cadastro sem vinculação
    public static Veiculo cadastrarVeiculo(VeiculoController vController, Scanner scan) {

        System.out.println("\n--- Cadastro de Veículo ---");
        
        System.out.print("Placa: ");
        String placa = scan.nextLine().trim();

        if (!Validadores.ValidaPlaca(placa)) {
            System.out.println("Placa no formato inválido.");
            return null;
        }

        System.out.print("Modelo: ");
        String modelo = scan.nextLine().trim();

        System.out.print("Tipo: ");
        String tipo = scan.nextLine().trim();

        if (placa.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return null;
        }

        Residente residente = null;

        try {
            Veiculo veiculo = new Veiculo(placa, modelo, tipo, residente);
            vController.cadastrarVeiculo(veiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
            return veiculo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void listarVeiculos(VeiculoController pController) {
        List<Veiculo> veiculos = pController.listarVeiculos();

        for (Veiculo v : veiculos) {
            System.out.println( "Placa: " + v.getPlaca() + 
                                ", Tipo: " + v.getTipo() + 
                                ", Modelo: " + v.getModelo());
        }
    }

}
