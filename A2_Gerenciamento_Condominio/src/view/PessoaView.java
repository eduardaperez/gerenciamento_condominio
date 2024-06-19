package view;

import model.Pessoa;
import model.Residente;
import model.Veiculo;
import model.Visitante;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import util.*;
import controller.PessoaController;
import controller.VeiculoController;


public class PessoaView {

    public static void cadastrarResidente(PessoaController pController, VeiculoController vController, Scanner scanner) {
        System.out.println("\n--- Cadastro de Residente ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }

        System.out.print("Cpf: ");
        String cpf = scanner.nextLine().trim();
        if (!Validadores.ValidaCpf(cpf)) {
            System.out.println("CPF no formato inválido.");
            return;
        }

        System.out.print("Bloco: ");
        int bloco = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Apartamento: ");
        int apartamento = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();

        if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento no formato inválido.");
            return;
        }

        Residente residente = new Residente(nome, telefone, cpf, bloco, apartamento, dataNascimento, null);

        System.out.print("Possui veículo? [0 - não, 1 - sim]: ");
        int possuiVeiculo = scanner.nextInt();
        scanner.nextLine();

        if (possuiVeiculo == 1) {
            System.out.print("Veículo já está cadastrado? [0 - não, 1 - sim]: ");
            int veiculoCadastrado = scanner.nextInt();
            scanner.nextLine();

            if (veiculoCadastrado == 1) {
                System.out.print("Informe a placa do veículo: ");
                String placa = scanner.nextLine().trim();
                Veiculo veiculo = vController.obterVeiculo(placa);
                if (veiculo != null) {
                    residente.setVeiculos(List.of(veiculo));
                    System.out.println("Veículo vinculado ao residente.");
                } else {
                    System.out.println("Veículo não encontrado.");
                }
            } else if (veiculoCadastrado == 0) {
                Veiculo veiculo = VeiculoView.cadastrarVeiculo(vController, scanner);
                if (veiculo != null) {
                    residente.setVeiculos(List.of(veiculo));
                    vController.cadastrarVeiculo(veiculo);
                    System.out.println("Veículo cadastrado e vinculado ao residente.");
                }
            }
        }

        if(pController.cadastrarResidente(residente))
            System.out.println("Residente cadastrado com sucesso.");
        else
            System.out.println("Erro ao cadastrar residente");
    }

    public static void cadastrarVisitante(PessoaController pController, Scanner scanner) {
        System.out.println("\n--- Cadastro de Visitante ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }

        if (nome.isEmpty() || telefone.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        System.out.print("Está visitando o bloco: ");
        int blocoVisita = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Apartamento: ");
        int apartamentoVisita = scanner.nextInt();
        scanner.nextLine();

        Residente residente = pController.buscarResidentePorBlocoEApartamento(blocoVisita, apartamentoVisita);

        if (residente == null) {
            System.out.println("Residente não encontrado!");
            return;
        }

        System.out.print("O residente encontrado é " + residente.getNome() + "? [0 - não, 1 - sim]: ");
        int confirmaResidente = scanner.nextInt();
        scanner.nextLine(); 

        if (confirmaResidente == 0) {
            System.out.println("Residente não confere com a pessoa que o visitante gostaria de visitar.");
            return;
        }

        Visitante visitante = new Visitante(nome, telefone);
        visitante.setResidente(residente);

        visitante.setId(pController.gerarIdVisitante());

        if (pController.cadastrarVisitante(visitante)) {
            System.out.println("Visitante cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao registrar visitante");
        }
    }

    public static void listarResidentes(PessoaController pController) {
        List<Residente> residentes = pController.listarResidentes();

        System.out.println("\n--- Lista de residentes ---");
        for (Residente m : residentes) {
            System.out.println("Nome: " + m.getNome() + ", CPF: " + m.getCpf() + ", Bloco: " + m.getBloco() +  ", Apartamento: " + m.getApartamento() + ", Contato: " + m.getContato());
        }
    }

    public static void listarVisitantes(PessoaController pController) {
        List<Visitante> visitantes = pController.listarVisitantes();

        System.out.println("\n--- Lista de Visitantes ---");
        for (Visitante v : visitantes) {
            System.out.println("ID: " + v.getId() + ", Nome: " + v.getNome() + ", Contato: " + v.getContato() + ", Telefone: ");
        }
    }
}