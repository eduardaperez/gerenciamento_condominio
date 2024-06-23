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
        String placa = scan.nextLine().trim();

        if (!Validadores.ValidaPlaca(placa)) {
            System.out.println("Placa no formato inválido.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scan.nextLine().trim();

        System.out.print("Tipo [1 - Carro, 2 - Moto]: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        String tipo = null;
        
        if (opcao != 1 && opcao != 2) {
            System.out.println("Opção inválida. Digite 1 para Carro e 2 para Moto");
            return;
        }

        switch (opcao) {
            case 1:
                tipo = "Carro";
                break;
            case 2:
                tipo = "Moto";
                break;
            default:
                break;
        }

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
        } else if (tipoCadastro != 1 && tipoCadastro != 2) {
            System.out.println("Opção inválida. Digite 1 para Residente e 2 para Visitante.");
            return;
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

        System.out.print("Tipo [1 - Carro, 2 - Moto]: ");
        int opcao = scan.nextInt();
        scan.nextLine();

        String tipo = null;
        
        if (opcao != 1 && opcao != 2) {
            System.out.println("Opção inválida. Digite 1 para Carro e 2 para Moto");
            return null;
            }

        switch (opcao) {
            case 1:
                tipo = "Carro";
                break;
            case 2:
                tipo = "Moto";
                break;
            default:
                break;
        }

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

    public static void atualizarVeiculo (VeiculoController vController, Scanner scanner) {
        System.out.println("\n--- Atualização de Veículo ---");
        
        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim();

        if (!Validadores.ValidaPlaca(placa)) {
            System.out.println("Placa no formato inválido.");
            return;
        }

        Veiculo veiculo = vController.obterVeiculo(placa);

        if (veiculo == null) {
            System.out.println("Veiculo não encontrado!");
            return;
        }

        System.out.println("-> Veiculo encontrado \nModelo: " + veiculo.getModelo() + "\nTipo: " + veiculo.getTipo());
        System.out.println("Gostaria de atualizar o registro? [s/n]");
        String opcao = scanner.nextLine().toUpperCase();

        if (opcao.equalsIgnoreCase("n")) {
            System.out.println("Processo de atualização cancelado!");
            return;
        } else if (!opcao.equalsIgnoreCase("s") && !opcao.equalsIgnoreCase("n")) {
            System.out.println("\nOpção inválida. Processo de atualização cancelado.\n");
            return;
        }

        try{

            System.out.println("Placa anterior:" + veiculo.getPlaca() + " \nPlaca novo: ");
            String placaNovo = scanner.nextLine().trim();
    
            if (!Validadores.ValidaPlaca(placaNovo)) {
                System.out.println("Placa no formato inválido.");
                return;
            }
    
            System.out.println("Modelo anterior:" + veiculo.getModelo() + " \nModelo novo: ");
            String modeloNovo = scanner.nextLine();
        
            
            System.out.println("Tipo anterior:" + veiculo.getTipo() + " \nTipo novo: ");
            System.out.print("Tipo [1 - Carro, 2 - Moto]: ");
            int opcaoTipoNovo = scanner.nextInt();
            scanner.nextLine();

            String tipoNovo = scanner.nextLine();
        
            if (opcaoTipoNovo != 1 && opcaoTipoNovo != 2) {
                System.out.println("Opção inválida. Digite 1 para Carro e 2 para Moto");
                return;
            }

            switch (opcaoTipoNovo) {
                case 1:
                    tipoNovo = "Carro";
                break;
                case 2:
                    tipoNovo = "Moto";
                break;
                default:
                break;
            }
            
            vController.atualizarVeiculo(veiculo, placaNovo, tipoNovo, modeloNovo);
            System.out.println("\nAtualização concluida!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar veiculo: " + e.getMessage());
        }
    }

    public static void excluirVeiculo(VeiculoController vController, Scanner scanner) {
        System.out.println("\n--- Exclusão de Veiculo ---");

        System.out.print("Insira a placa do Veiculo: ");
        String placa = scanner.nextLine().trim();

        if (!Validadores.ValidaPlaca(placa)) {
            System.out.println("Placa no formato inválido.");
            return;
        }

        Veiculo veiculo = vController.obterVeiculo(placa);

        if (veiculo == null) {
            System.out.println("Veiculo não encontrado!");
            return;
        }

        System.out.println("Veiculo encontrado: " + veiculo.getModelo());
        System.out.println("Tem certeza que deseja deletar este veiculo? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            try {
                System.out.println("Veiculo excluido com sucesso.");
                vController.removerVeiculo(placa);
            } catch (Exception e) {
                System.out.println("Erro ao excluir Veiculo: " + e.getMessage());
            }
        } else {
            System.out.println("Operação de exclusão cancelada");
        }
    }

    public static void listarVeiculos(VeiculoController vController) {
        List<Veiculo> veiculos = vController.listarVeiculos();

        for (Veiculo v : veiculos) {
            System.out.println( "Placa: " + v.getPlaca() + 
                                ", Tipo: " + v.getTipo() + 
                                ", Modelo: " + v.getModelo());
        }
    }

    // Informações de Veículo
    public static void exibirInfoVeiculo(Veiculo veiculo) {
        System.out.println("Placa: " + veiculo.getPlaca() + 
                            "\nTipo: " + veiculo.getTipo() +
                            "\nModelo: " + veiculo.getModelo());
    }

}
