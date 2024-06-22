package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import controller.AcessoController;
import controller.PessoaController;
import controller.VeiculoController;
import model.Acesso;
import model.Residente;
import model.Veiculo;
import model.Visitante;
import util.Validadores;

public class AcessoView {

    // Dados de acesso de Residente
    public static void acessoResidente(PessoaController pController, AcessoController aController, Scanner scan) {

        System.out.println("--- Acesso Residente ---");
        System.out.println("CPF: ");
        String cpfResidente = scan.nextLine().trim();
        if (!Validadores.ValidaCpf(cpfResidente)) {
            System.out.println("CPF no formato inválido.");
            return;
        }

        if (cpfResidente.isEmpty()) {
            System.out.println("Acesso negado, insira seu CPF.");
            return;
        } 

        try {
            Residente residente = pController.obterResidente(cpfResidente);
            if (residente == null) {
                System.out.println("Residente não encontrado, tente novamente");
            } else {
                aController.registrarEntradaResidente(residente, LocalDateTime.now());
                System.out.println("Entrada registrada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar a entrada: " + e.getMessage());
        }
    }

    // Dados de acesso de Visitante
    public static void acessoVisitante(PessoaController pController, AcessoController aController, Scanner scan) {

        System.out.println("--- Acesso Visitante ---");
        System.out.println("Bloco Residente: ");
        int blocoResidente = scan.nextInt();
        System.out.println("Apartamento Residente: ");
        int aptoResidente = scan.nextInt();

        if (blocoResidente == 0  || aptoResidente == 0) {
            System.out.println("Acesso negado, insira os dados necessários.");
            return;
        } 

        try {
            Residente residente = pController.buscarResidentePorBlocoEApartamento(blocoResidente, aptoResidente);
            if (residente == null) {
                System.out.println("Residente não encontrado, tente novamente");
            } else {
                System.out.println("Já possui cadastro? [1 - Sim | 2 - Não]");
                int possuiCadastro = scan.nextInt();
                scan.nextLine();

                System.out.print("Nome: ");
                String nome = scan.nextLine().trim();

                System.out.print("Telefone: ");
                String telefone = scan.nextLine().trim();
                if (!Validadores.ValidaContato(telefone)) {
                    System.out.println("Telefone no formato inválido.");
                    return;
                }
                if (possuiCadastro == 1) {
                    Visitante visitante = pController.obterVisitantePorNomeETelefone(nome, telefone);
                    
                    aController.registrarEntradaVisitante(visitante, LocalDateTime.now());
                    System.out.println("Entrada registrada com sucesso.");
                } else if (possuiCadastro ==2) {
                    PessoaView.cadastrarVisitante(nome, telefone, blocoResidente, aptoResidente, pController, scan);
                    Visitante visitante = pController.obterVisitantePorNomeETelefone(nome, telefone);
                    //fazer acesso após cadastro
                    aController.registrarEntradaVisitante(visitante, LocalDateTime.now());
                    System.out.println("Entrada registrada com sucesso.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar a entrada: " + e.getMessage());
        }
    }

    // Dados de acesso de Veículo
    public static void acessoVeiculo(PessoaController pController, VeiculoController vController, AcessoController aController, Scanner scan) {

        System.out.println("--- Acesso Veículo ---");
        System.out.println("Placa: ");
        String placaVeiculo = scan.nextLine().trim();

        if (placaVeiculo.isEmpty()) {
            System.out.println("Acesso negado, insira os dados necessários.");
            return;
        } 

        if (!Validadores.ValidaPlaca(placaVeiculo)) {
            System.out.println("Placa no formato inválido.");
            return;
        }

        try {
            Veiculo veiculo = vController.obterVeiculo(placaVeiculo);
            if (veiculo == null) {
                System.out.println("Veículo não encontrado, encaminhando para cadastro ...");
                System.out.println(".............................");
                veiculo = VeiculoView.cadastrarVeiculo(vController, scan);
                aController.registrarEntradaVeiculo(veiculo, LocalDateTime.now());
            } else {
                aController.registrarEntradaVeiculo(veiculo, LocalDateTime.now());
                System.out.println("Entrada registrada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar a entrada: " + e.getMessage());
        }
    }

    //Listas

    // Informações de Residente
    private static void exibirInfoResidente(Residente residente) {
        System.out.println("Nome: " + residente.getNome() + 
                           "\nContato: " + residente.getContato());
    }

    // Informações de Visitante
    private static void exibirInfoVisitante(Visitante visitante, PessoaController pController) {
        System.out.println("Nome: " + visitante.getNome() + 
                           "\nContato: " + visitante.getContato());
        Residente residente = pController.obterResidente(visitante.getResidente().getCpf());
        System.out.println("Visitando: ");
        exibirInfoResidente(residente);
    }

    // Informações de Veículo
    private static void exibirInfoVeiculo(Veiculo veiculo) {
        System.out.println("Placa: " + veiculo.getPlaca() + 
                           "\nTipo: " + veiculo.getTipo() +
                           "\nModelo: " + veiculo.getModelo());
    }

    public static void exibirAcessos(AcessoController aController, VeiculoController vController, PessoaController pController) {

        exibirAcessosVeiculos(aController, vController);
        exibirAcessosResidentes(aController, pController);
        exibirAcessosVisitantes(aController, pController);

    }

    public static void exibirAcessosResidentes(AcessoController aController, PessoaController pController) {
        List<Acesso> acessos = aController.listarAcessos();

        System.out.println("\n -> Entrada Residente: " );
        for (Acesso a : acessos) {
            if (a.getResidente() != null) {
                System.out.println("Id: " + a.getId() + 
                                   "\nData: " + a.getEntrada());
                
                System.out.println("\nDados Residente: ");
                exibirInfoResidente(a.getResidente());
            }
        }
    }

    public static void exibirAcessosVisitantes(AcessoController aController, PessoaController pController) {
        List<Acesso> acessos = aController.listarAcessos();

        System.out.println("\n -> Entrada Visitante: " );
        for (Acesso a : acessos) {
            if (a.getVisitante() != null) {
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                System.out.println("\nDados Visitante: ");
                exibirInfoVisitante(a.getVisitante(), pController);
            }
        }
    }

    public static void exibirAcessosVeiculos(AcessoController aController, VeiculoController vController) {
        List<Acesso> acessos = aController.listarAcessos();

        System.out.println("\n -> Entrada Veículo: " );
        for (Acesso a : acessos) {
            if (a.getVeiculo() != null) {
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                System.out.println("\nDados Veículo: ");
                exibirInfoVeiculo(a.getVeiculo());
            }
        }
    }

    public static void exibirAcessosDia(AcessoController aController, PessoaController pController, VeiculoController vController, Scanner scan) {
        System.out.println("Data solicitada (dd/mm/yyyy): ");
        String diaStr = scan.nextLine().trim();

        LocalDate dia;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dia = LocalDate.parse(diaStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data no formato inválido.");
            return;
        }

        List<Acesso> acessos = aController.listarAcessosDia(dia);
        for (Acesso a : acessos) {
            System.out.println("Id: " + a.getId() + ", Data: " + a.getEntrada());
            if (a.getResidente() != null) {
                System.out.println("\n -> Entrada Residente: ");
                exibirInfoResidente(a.getResidente());
            } else if (a.getVisitante() != null) {
                System.out.println("\n -> Entrada Visitante: ");
                exibirInfoVisitante(a.getVisitante(), pController);
            } else if (a.getVeiculo() != null) {
                System.out.println("\n -> Entrada Veículo: ");
                exibirInfoVeiculo(a.getVeiculo());
            }
        }
    }

}

