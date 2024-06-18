package view;

import java.util.Scanner;

import controller.PessoaController;
import controller.VeiculoController;
import model.Residente;
import model.Veiculo;

public class VeiculoView {

    public static void cadastrarVeiculo(VeiculoController vController, PessoaController pController, Scanner scan) {

        System.out.println("\n--- Cadastro de Veículo ---");
        System.out.print("Placa: ");
        String placa = scan.nextLine().trim();
        System.out.print("Modelo: ");
        String modelo = scan.nextLine().trim();
        System.out.print("Tipo: ");
        String tipo = scan.nextLine().trim();

        if (placa.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        // Verifica se o cadastro é para um residente ou visitante
        System.out.print("Cadastro para [1 - Residente, 2 - Visitante]: ");
        int tipoCadastro = scan.nextInt();
        scan.nextLine(); 

        Residente residente = null;
        if (tipoCadastro == 1) {
            System.out.print("CPF do Residente associado: ");
            String residenteCpf = scan.nextLine();

            residente = pController.obterResidente(residenteCpf);
            if (residente == null) {
                System.out.println("Residente não encontrado!");
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

}
