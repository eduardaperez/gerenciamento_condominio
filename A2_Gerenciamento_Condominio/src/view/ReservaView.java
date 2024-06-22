package view;

import controller.ReservaController;
import controller.PessoaController;
import model.Reserva;
import model.Residente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ReservaView {

    public static void iniciarReserva(Scanner scanner, PessoaController pessoaController, ReservaController reservaController) {
        System.out.println("--- Reserva de Espaços Comuns ---");
        System.out.print("Digite o seu CPF: ");
        String cpfResidente = scanner.nextLine();

        Residente residente = pessoaController.obterResidente(cpfResidente);
        if (residente != null) {
            exibirMenuReservas(residente, scanner, reservaController);
        } else {
            System.out.println("Residente não encontrado.");
        }
    }

    public static void exibirMenuReservas(Residente residente, Scanner scanner, ReservaController reservaController) {
        int opcao;

        do {
            System.out.println("\n--- Menu de Reservas ---");
            System.out.println("1. Churrasqueira");
            System.out.println("2. Salão de Festas");
            System.out.println("3. Piscina");
            System.out.println("4. Quadra");
            System.out.println("5. Cancelar Reserva");
            System.out.println("6. Lista de Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        reservarEspaco("Churrasqueira", residente, scanner, reservaController);
                        break;
                    case 2:
                        reservarEspaco("Salão de Festas", residente, scanner, reservaController);
                        break;
                    case 3:
                        reservarEspaco("Piscina", residente, scanner, reservaController);
                        break;
                    case 4:
                        reservarEspaco("Quadra", residente, scanner, reservaController);
                        break;
                    case 5:
                        cancelarReservaPorResidente(residente, scanner, reservaController);
                        break;
                    case 6:
                        listarDatasOcupadas(scanner, reservaController);
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
                opcao = -1;
            }
        } while (opcao != 0);
    }

    public static void reservarEspaco(String area, Residente residente, Scanner scanner, ReservaController reservaController) {
        try {
            System.out.print("Data da reserva (dd-MM-yyyy): ");
            String dataHora = scanner.nextLine();
            LocalDate dataReserva = LocalDate.parse(dataHora, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (reservaController.pesquisarDisponibilidadePorData(dataReserva, area)) {
                Reserva reserva = new Reserva(reservaController.gerarIdReserva(), residente, area, dataReserva);
                reservaController.adicionarReserva(reserva);
                System.out.println("Reserva realizada com sucesso para " + area + ".");
            } else {
                System.out.println("A área " + area + " já está reservada para esta data. ");
            }
        } catch (Exception e) {
            System.out.println("Erro ao realizar a reserva: " + e.getMessage());
        }
    }

    public static void listarDatasOcupadas(Scanner scanner, ReservaController reservaController) {
        int opcaoArea; 
        String areaSelecionada = null;

        System.out.println("\n--- Listagem de Datas Ocupadas ---");
        System.out.println("Selecione a área para listar as datas ocupadas:");
        System.out.println("1. Churrasqueira");
        System.out.println("2. Salão de Festas");
        System.out.println("3. Piscina");
        System.out.println("4. Quadra");
        System.out.print("Escolha uma opção: ");

        try {
            opcaoArea = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcaoArea) {
                case 1:
                    areaSelecionada = "Churrasqueira";
                    break;
                case 2:
                    areaSelecionada = "Salão de Festas";
                    break;
                case 3:
                    areaSelecionada = "Piscina";
                    break;
                case 4:
                    areaSelecionada = "Quadra";
                    break;
                default:
                    System.out.println("Opção inválida. Voltando ao menu principal.");
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.nextLine();
            opcaoArea = -1;
        }

        List<String> datasOcupadas = reservaController.listarDatasOcupadasPorArea(areaSelecionada);
        if (datasOcupadas.isEmpty()) {
            System.out.println("Não há datas ocupadas para a área " + areaSelecionada);
        } else {
            System.out.println("Datas ocupadas para a área " + areaSelecionada + ":");
            for (String data : datasOcupadas) {
                System.out.println(data);
            }
        }
    }

    public static void cancelarReservaPorResidente(Residente residente, Scanner scanner, ReservaController reservaController) {
        List<Reserva> reservasDoResidente = reservaController.buscarReservasPorNomeCpf(residente.getCpf());

        if (reservasDoResidente.isEmpty()) {
            System.out.println("Não há reservas encontradas para o residente com CPF: " + residente.getCpf());
        } else {
            System.out.println("Reservas encontradas para o residente com CPF: " + residente.getCpf() + ":");
            for (Reserva reserva : reservasDoResidente) {
                System.out.println("ID: " + reserva.getId() + ", Área: " + reserva.getArea() + ", Data: " + reserva.getDataReserva());
            }

            System.out.print("Digite o ID da reserva que deseja cancelar: ");
            int idReserva;
            try {
                idReserva = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro para o ID da reserva.");
                scanner.nextLine();
                return;
            }

            while (true) {
                System.out.print("Tem certeza que deseja cancelar esta reserva? (s/n): ");
                String confirmacao = scanner.nextLine();
                if (confirmacao.equalsIgnoreCase("s")) {
                    reservaController.cancelarReserva(idReserva);
                    System.out.println("Reserva cancelada com sucesso.");
                    break;
                } else if (confirmacao.equalsIgnoreCase("n")) {
                    System.out.println("Operação de cancelamento de reserva cancelada pelo usuário.");
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, digite 's' para sim ou 'n' para não.");
                }
            }
        }
    }}
