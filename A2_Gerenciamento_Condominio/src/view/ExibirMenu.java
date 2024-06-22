package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.AcessoController;
import controller.EntregaController;
import controller.PessoaController;
import controller.ReservaController;
import controller.VeiculoController;

public class ExibirMenu {

    public static void exibirMenuPrincipal(Scanner scanner, AcessoController acessoController, EntregaController entregaController, PessoaController pessoaController, ReservaController reservaController, VeiculoController veiculoController) {
        int opcao;

        do {
            System.out.println(); // Pula linha
            System.out.println("----- Menu -----");
            System.out.println("1. Registros");
            System.out.println("2. Controle de Acesso");
            System.out.println("3. Entregas");
            System.out.println("4. Reservas");
            System.out.println("5. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcao) {
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    case 1:
                        exibirMenuRegistros(scanner, pessoaController, veiculoController);
                        break;
                    case 2:
                        exibirMenuControleAcesso(acessoController, pessoaController, veiculoController, scanner);
                        break;
                    case 3:
                        exibirMenuEntregas(scanner, entregaController, pessoaController);
                        break;
                    case 4:
                        ReservaView.iniciarReserva(scanner, pessoaController, reservaController);
                        break;
                    case 5:
                        exibirMenuRelatorios(scanner, acessoController, entregaController, pessoaController, reservaController, veiculoController);
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); 
                opcao = -1; // Reinicia a opcao
            }
        } while (opcao != 0);
    }

    public static void exibirMenuControleAcesso(AcessoController acessoController, PessoaController pessoaController, VeiculoController veiculoController, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Controle de Acesso ---");
            System.out.println("1. Entrada de Residente");
            System.out.println("2. Entrada de Visitante");
            System.out.println("3. Entrada de Veículo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Entrada de Residente selecionada.");
                    System.out.println("_________________________________");
                    AcessoView.acessoResidente(pessoaController, acessoController, scanner);
                    break;
                case 2:
                    System.out.println("Entrada de visitante selecionada.");
                    System.out.println("_________________________________");
                    AcessoView.acessoVisitante(pessoaController, acessoController, scanner);
                    break;
                case 3:
                    System.out.println("Entrada de Veículo selecionada.");
                    System.out.println("_________________________________");
                    AcessoView.acessoVeiculo(pessoaController, veiculoController, acessoController, scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuEntregas(Scanner scanner, EntregaController entregaController, PessoaController pessoaController) {
        int opcao;
        do {
            System.out.println("\n--- Entregas ---");
            System.out.println("1. Registrar Chegada de Encomenda");
            System.out.println("2. Retirada de Encomendas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.println("Registrar Chegada de Encomenda selecionada.");
                    EntregaView.registrarEntrega(entregaController, pessoaController, scanner);
                    break;
                case 2:
                    System.out.println("Associar Encomenda a Residente selecionada.");
                    EntregaView.registrarRetirada(entregaController, pessoaController, scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirMenuRegistros(Scanner scanner, PessoaController pessoaController, VeiculoController veiculoController) {
        int opcao;
        do {
            System.out.println("\n--- Cadastros ---");
            System.out.println("1. Cadastro de Pessoa Física");
            System.out.println("2. Cadastrar Veículos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try{
                opcao = scanner.nextInt();
                scanner.nextLine(); 
                switch (opcao) {
                    case 1:
                        System.out.println("\nCadastrar Pessoa Física selecionado.");
                        System.out.println("------------------------------------");
                        
                        System.out.print("Selecione uma opção? [1 - Residente, 2 - Visitante]:\n");
                        int tipoCadastro = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoCadastro == 1) 
                            PessoaView.cadastrarResidente(pessoaController, veiculoController, scanner);

                        else if (tipoCadastro == 2) 
                            PessoaView.cadastrarVisitante(pessoaController, scanner);

                        break;
    
                    case 2:
                        System.out.println("Cadastrar Veículo selecionado.");
                        System.out.println("------------------------------------");
                        VeiculoView.cadastrarVeiculo(veiculoController, pessoaController, scanner);
                        break;
                        
                    case 0:
                        System.out.println("Voltando ao menu principal...");
                        break;
    
                    default:
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); 
                opcao = -1; // Reinicia a opcao
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

    public static void exibirMenuRelatorios(Scanner scanner, AcessoController acessoController, EntregaController entregaController, PessoaController pessoaController, ReservaController reservaController, VeiculoController veiculoController) {
        int opcao;
        do {
            System.out.println("\n--- Relatórios ---");
            System.out.println("1. Relatório de Acessos");
            System.out.println("2. Relatório de Pessoas");
            System.out.println("3. Relatório de Entregas");
            System.out.println("4. Relatório de Reservas");
            System.out.println("5. Relatório de Veiculos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.println("Relatório de Acessos selecionado.");
                    exibirTiposAcesso(acessoController, entregaController, veiculoController, pessoaController, reservaController, scanner);
                break;
                case 2:
                    System.out.println("\nRelatório de Visitantes selecionado.");
                    System.out.println("------------------------------------");
                    System.out.println("Disponiveis: ");
                    System.out.println("\n1. Moradores Cadastrados \n2. Visitantes Cadastrados");
                    System.out.print("Selecione uma opção: ");
                    int tipoPessoaOpcao = scanner.nextInt();
                    scanner.nextLine();

                    if(tipoPessoaOpcao == 1)
                        PessoaView.listarResidentes(pessoaController);

                    else if(tipoPessoaOpcao == 2)
                        PessoaView.listarVisitantes(pessoaController);
                        
                    break;
                case 3:
                    System.out.println("Relatório de Entregas selecionado.");
                    System.out.println("------------------------------------");

                    System.out.print("Selecione uma opção: ");
                    System.out.println("\n1. Entregas não retiradas \n2. Entregas retiradas");

                    int opcaoEntrega = scanner.nextInt();
                    scanner.nextLine();

                    if(opcaoEntrega == 1)
                        EntregaView.exibirlistarEntregasNaoRetiradas(entregaController);

                    else if(opcaoEntrega == 2)
                        EntregaView.exibirListaEntregas(entregaController);

                    break;
                    
                case 4:
                    System.out.println("\nRelatório de Reservas selecionado.");
                    System.out.println("------------------------------------");
                    ReservaView.listarTodasReservasFeitas(reservaController);

                    break;
                case 5:
                    System.out.println("\nRelatório Veiculos Selecionado.");
                    System.out.println("------------------------------------");
                    VeiculoView.listarVeiculos(veiculoController);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    exibirMenuPrincipal(scanner, acessoController, entregaController, pessoaController, reservaController, veiculoController);
                break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void exibirTiposAcesso(AcessoController acessoController, EntregaController entregaController, VeiculoController veiculoController, PessoaController pessoaController, ReservaController reservaController, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Acessos ---");
            System.out.println("1. Todos os acessos");
            System.out.println("2. Acessos do dia");
            System.out.println("3. Acesso de Residentes");
            System.out.println("4. Acesso de Visitantes");
            System.out.println("5. Acesso de Veículos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            switch (opcao) {
                case 1:
                    System.out.println("--- Todos os acessos ---");
                    AcessoView.exibirAcessos(acessoController, veiculoController, pessoaController);
                break;
                case 2:
                    System.out.println("--- Acessos do dia ---");
                    AcessoView.exibirAcessosDia(acessoController, pessoaController, veiculoController, scanner);
                break;
                case 3:
                    System.out.println("--- Acesso de Residentes ---");
                    AcessoView.exibirAcessosResidentes(acessoController, pessoaController);
                break;
                case 4:
                    System.out.println("--- Acesso de Visitantes ---");
                    AcessoView.exibirAcessosVisitantes(acessoController, pessoaController);
                break;
                case 5:
                    System.out.println("--- Acesso de Veículos ---");
                    AcessoView.exibirAcessosVeiculos(acessoController, veiculoController);
                break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    exibirMenuRelatorios(scanner, acessoController, entregaController, pessoaController, reservaController, veiculoController);
                break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }
}
