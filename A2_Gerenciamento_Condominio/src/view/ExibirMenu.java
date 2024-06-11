package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExibirMenu {

    public static void exibirMenuPrincipal(Scanner scanner) {
        int opcao;
        do {
            System.out.println(); // Pula linha
            System.out.println("----- Menu -----");
            System.out.println("1. Controle de Acesso");
            System.out.println("2. Entregas");
            System.out.println("3. Cadastros");
            System.out.println("4. Reservas");
            System.out.println("5. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
                switch (opcao) {
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    case 1:
                        exibirMenuControleAcesso(scanner);
                        break;
                    case 2:
                        exibirMenuEntregas(scanner);
                        break;
                    case 3:
                        exibirMenuCadastros(scanner);
                        break;
                    case 4:
                        exibirMenuReservas(scanner);
                        break;
                    case 5:
                        exibirMenuRelatorios(scanner);
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); // Limpa o buffer
                opcao = -1; // Reinicia a opcao
            }
        } while (opcao != 0);
    }

    public static void exibirMenuControleAcesso(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Controle de Acesso ---");
            System.out.println("1. Entrada de Pessoa");
            System.out.println("2. Saída de Pessoa");
            System.out.println("3. Entrada de Veículo");
            System.out.println("4. Saída de Veículo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Entrada de Pessoa selecionada.");
                    // Implementar lógica para Entrada de Pessoa
                    break;
                case 2:
                    System.out.println("Saída de Pessoa selecionada.");
                    // Implementar lógica para Saída de Pessoa
                    break;
                case 3:
                    System.out.println("Entrada de Veículo selecionada.");
                    // Implementar lógica para Entrada de Veículo
                    break;
                case 4:
                    System.out.println("Saída de Veículo selecionada.");
                    // Implementar lógica para Saída de Veículo
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuEntregas(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Entregas ---");
            System.out.println("1. Registrar Chegada de Encomenda");
            System.out.println("2. Associar Encomenda a Residente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Registrar Chegada de Encomenda selecionada.");
                    // Implementar lógica para Registrar Chegada de Encomenda
                    break;
                case 2:
                    System.out.println("Associar Encomenda a Residente selecionada.");
                    // Implementar lógica para Associar Encomenda a Residente
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuCadastros(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Cadastros ---");
            System.out.println("1. Cadastro de Pessoa Física");
            System.out.println("2. Cadastro de Veículos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    exibirMenuCadastroPessoaFisica(scanner);
                    break;
                case 2:
                    exibirMenuCadastroVeiculo(scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuCadastroPessoaFisica(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Cadastro de Pessoa Física ---");
            System.out.println("1. Cadastrar Residente");
            System.out.println("2. Cadastrar Visitante");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Residente selecionado.");
                    // Implementar lógica para Cadastrar Residente
                    break;
                case 2:
                    System.out.println("Cadastrar Visitante selecionado.");
                    // Implementar lógica para Cadastrar Visitante
                    break;
                case 0:
                    System.out.println("Voltando ao menu de cadastros...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuCadastroVeiculo(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Cadastro de Veículos ---");
            System.out.println("1. Cadastrar Veículo de Residente");
            System.out.println("2. Cadastrar Veículo de Visitante");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Veículo de Residente selecionado.");
                    // Implementar lógica para Cadastrar Veículo de Residente
                    break;
                case 2:
                    System.out.println("Cadastrar Veículo de Visitante selecionado.");
                    // Implementar lógica para Cadastrar Veículo de Visitante
                    break;
                case 0:
                    System.out.println("Voltando ao menu de cadastros...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuReservas(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Reservas ---");
            System.out.println("1. Reservar Espaço Comum");
            System.out.println("2. Listar Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Reservar Espaço Comum selecionado.");
                    // Implementar lógica para Reservar Espaço Comum
                    break;
                case 2:
                    System.out.println("Listar Reservas selecionado.");
                    // Implementar lógica para Listar Reservas
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuRelatorios(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Relatórios ---");
            System.out.println("1. Relatório de Acessos");
            System.out.println("2. Relatório de Visitantes");
            System.out.println("3. Relatório de Entregas");
            System.out.println("4. Relatório de Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("Relatório de Acessos selecionado.");
                    // Implementar lógica para Relatório de Acessos
                    break;
                case 2:
                    System.out.println("Relatório de Visitantes selecionado.");
                    // Implementar lógica para Relatório de Visitantes
                    break;
                case 3:
                    System.out.println("Relatório de Entregas selecionado.");
                    // Implementar lógica para Relatório de Entregas
                    break;
                case 4:
                    System.out.println("Relatório de Reservas selecionado.");
                    // Implementar lógica para Relatório de Reservas
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }
}
