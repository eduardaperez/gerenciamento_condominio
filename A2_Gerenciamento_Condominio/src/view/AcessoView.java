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

        Residente residente = null;

        try {
            residente = pController.obterResidente(cpfResidente);
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
        System.out.println("Nome Residente: ");
        String nomeResidente = scan.nextLine().trim();
        System.out.println("Bloco Residente: ");
        int blocoResidente = scan.nextInt();
        System.out.println("Apartamento Residente: ");
        int aptoResidente = scan.nextInt();

        if (nomeResidente.isEmpty() || blocoResidente == 0  || aptoResidente == 0) {
            System.out.println("Acesso negado, insira os dados necessários.");
            return;
        } 

        Residente residente = null;

        try {
            residente = pController.buscarResidentePorBlocoEApartamento(blocoResidente, aptoResidente);
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
                    PessoaView.cadastrarVisitante(pController, scan);
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

        Veiculo veiculo = null;

        try {
            veiculo = vController.obterVeiculo(placaVeiculo);
            if (veiculo == null) {
                System.out.println("Veículo não encontrado, encaminhando para cadastro ...");
                System.out.println(".............................");
                VeiculoView.cadastrarVeiculo(vController, pController, scan);
                aController.registrarEntradaVeiculo(veiculo, LocalDateTime.now());
            } else {
                aController.registrarEntradaVeiculo(veiculo, LocalDateTime.now());
                System.out.println("Entrada registrada com sucesso.");
                aController.listarAcessos();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar a entrada: " + e.getMessage());
        }
    }

    //Listas

    public static void exibirAcessos(AcessoController aController, VeiculoController vController, PessoaController pController) {

        exibirAcessosVeiculos(aController, vController);
        exibirAcessosResidentes(aController, pController);
        exibirAcessosVisitantes(aController, pController);

    }

    public static void exibirAcessosResidentes(AcessoController aController, PessoaController pController) {
        List<Acesso> acessos = aController.listarAcessos();

        for (Acesso a : acessos) {
            if (a.getResidente() != null) {
                System.out.println("Entrada Residente: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Residente residente = pController.obterResidente(a.getResidente().getCpf());
                System.out.println("Nome: " + residente.getNome() + 
                                   ", CPF: " + residente.getCpf() +
                                   ", Contato: " + residente.getContato());
            }
        }
    }

    public static void exibirAcessosVisitantes(AcessoController aController, PessoaController pController) {
        List<Acesso> acessos = aController.listarAcessos();

        for (Acesso a : acessos) {
            if (a.getVisitante() != null) {
                System.out.println("Entrada Visitante: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Visitante visitante = pController.obterVisitante(a.getVisitante().getId());
                System.out.println("Nome: " + visitante.getNome() + 
                                   ", Contato: " + visitante.getContato());
                Residente residente = pController.obterResidente(visitante.getResidente().getCpf());
                System.out.println("Visitando: ");
                System.out.println("Nome: " + residente.getNome() + 
                                   ", Bloco: " + residente.getBloco() +
                                   ", Apartamento: " + residente.getApartamento());
            }
        }
    }

    public static void exibirAcessosVeiculos(AcessoController aController, VeiculoController vController) {
        List<Acesso> acessos = aController.listarAcessos();

        for (Acesso a : acessos) {
            if (a.getVeiculo() != null) {
                System.out.println("Entrada Veículo: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Veiculo veiculo = vController.obterVeiculo(a.getVeiculo().getPlaca());
                System.out.println("Placa: " + veiculo.getPlaca() + 
                                   ", Tipo: " + veiculo.getTipo() +
                                   ", Modelo: " + veiculo.getModelo());
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
            if (a.getResidente() != null) {
                System.out.println("Entrada Residente: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Residente residente = pController.obterResidente(a.getResidente().getCpf());
                System.out.println("Nome: " + residente.getNome() + 
                                   ", CPF: " + residente.getCpf() +
                                   ", Contato: " + residente.getContato());
            } else if (a.getVisitante() != null) {
                System.out.println("Entrada Visitante: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Visitante visitante = pController.obterVisitante(a.getVisitante().getId());
                System.out.println("Nome: " + visitante.getNome() + 
                                   ", Contato: " + visitante.getContato());
                Residente residente = pController.obterResidente(visitante.getResidente().getCpf());
                System.out.println("Visitando: ");
                System.out.println("Nome: " + residente.getNome() + 
                                   ", Bloco: " + residente.getBloco() +
                                   ", Apartamento: " + residente.getApartamento());                
            } else if (a.getVeiculo() != null) {
                System.out.println("Entrada Veículo: " );
                System.out.println("Id: " + a.getId() + 
                                   ", Data: " + a.getEntrada());
                
                Veiculo veiculo = vController.obterVeiculo(a.getVeiculo().getPlaca());
                System.out.println("Placa: " + veiculo.getPlaca() + 
                                   ", Tipo: " + veiculo.getTipo() +
                                   ", Modelo: " + veiculo.getModelo());
            }
        }
    }

}

