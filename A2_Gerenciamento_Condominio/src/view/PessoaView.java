package view;

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
        String telefone = scanner.nextLine();
        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }
        telefone = telefone.trim().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");

        System.out.print("Cpf: ");
        String cpf = scanner.nextLine();
        if (!Validadores.ValidaCpf(cpf)) {
            System.out.println("CPF no formato inválido.");
            return;
        }
        cpf = cpf.trim().replace(".", "").replace("-", "").replace(" ", "");

        System.out.print("Bloco: ");
        int bloco = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Apartamento: ");
        int apartamento = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data de Nascimento (dd-MM-yyyy): ");
        String dataNascimentoStr = scanner.nextLine().trim();

        if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento no formato inválido.");
            return;
        }

        Residente residente = new Residente(nome, telefone, cpf, bloco, apartamento, dataNascimento, null);
        
        if(pController.cadastrarResidente(residente))
            System.out.println("Residente cadastrado com sucesso.");
        else
            System.out.println("Erro ao cadastrar residente");
            
        System.out.print("Possui veículo? [0 - não, 1 - sim]:\n");
        int possuiVeiculo = scanner.nextInt();
        scanner.nextLine();

        if (possuiVeiculo != 1 && possuiVeiculo != 2) {
            System.out.println("Opção inválida. Nenhum veículo cadastrado.");
        }

        if (possuiVeiculo == 1) {
            System.out.print("Veículo já está cadastrado? [0 - não, 1 - sim]:\n");
            int veiculoCadastrado = scanner.nextInt();
            scanner.nextLine();

            if (veiculoCadastrado == 1) {
                System.out.print("Informe a placa do veículo: ");
                String placa = scanner.nextLine().trim().toUpperCase();

                if (!Validadores.ValidaPlaca(placa)) {
                    System.out.println("Placa no formato inválido.");
                    return;
                }

                Veiculo veiculo = vController.obterVeiculo(placa);

                if (veiculo != null) {
                    residente.setVeiculos(List.of(veiculo));
                    System.out.println("Veículo vinculado ao residente.");

                } else {
                    System.out.println("Veículo não encontrado.");
                    return;
                }
                
            } else if (veiculoCadastrado == 0) {
                Veiculo veiculo = VeiculoView.cadastrarVeiculo(vController, scanner);
                if (veiculo != null) {
                    residente.setVeiculos(List.of(veiculo));
                    System.out.println("Veículo vinculado ao residente com sucesso!");
                }
            }
        }

    }

    public static void atualizaResidente(PessoaController pController, Scanner scanner){
        System.out.println("\n--- Atualização de Residente ---");

        System.out.print("Insira o cpf do Residente: ");
        String cpf = scanner.nextLine().trim();

        if (!Validadores.ValidaCpf(cpf)) {
            System.out.println("CPF no formato inválido.");
            return;
        }

        Residente residenteAntigo = pController.obterResidente(cpf);

        if (residenteAntigo == null) {
            System.out.println("residente não encontrado!");
            return;
        }

        System.out.println("Residente encontrado: " + residenteAntigo.getNome());
        System.out.print("Gostaria de atualizar o registro? [s/n]: ");
        String opcao = scanner.nextLine().trim().toUpperCase();

        if (!opcao.equals("S")) {
            System.out.println("Processo de atualização cancelado!");
            return;
        }

        try {
                System.out.println("Nome cadastrado:" + residenteAntigo.getNome() + " \nNome: ");
                String nome = scanner.nextLine();
    
                System.out.println("Telefone cadastrado:" + residenteAntigo.getContato() + " \nTelefone: ");
                String telefone = scanner.nextLine().trim();
    
                if (!Validadores.ValidaContato(telefone)) {
                    System.out.println("Telefone no formato inválido.");
                    return;
                }
    
                System.out.println("Bloco cadastrado:" + residenteAntigo.getBloco() + " \nBloco: ");
                int bloco = scanner.nextInt();
                scanner.nextLine();
    
                System.out.println("Apartamento cadastrado:" + residenteAntigo.getApartamento() + " \nApartamento: ");
                int apto = scanner.nextInt();
                scanner.nextLine();
    
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String dataNascimentoFormatada = residenteAntigo.getDataNascimento().format(formatter);        
                System.out.println("Data de nascimento cadastrada: " + dataNascimentoFormatada);
                System.out.print("Nova Data de Nascimento (dd-MM-yyyy): ");
                String dataNascimentoStr = scanner.nextLine().trim();
    
                LocalDate dataNascimento;
                dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
    
                pController.atualizarResidente(residenteAntigo, nome, telefone, bloco, apto, dataNascimento);;
    
                if (residenteAntigo.getVeiculos() != null && !residenteAntigo.getVeiculos().isEmpty()) {
                    List<Veiculo> veiculos = residenteAntigo.getVeiculos();
                    System.out.println("Veículo(s) cadastrado(s):");
                    veiculos.forEach(VeiculoView::exibirInfoVeiculo);
                    System.out.println("Se quiser atualizar os dados de veículos, por favor, acione o registro de veículos.");
                }
                System.out.println("\nAtualização concluida!");
            } catch (DateTimeParseException e) {
                System.out.println("Data de nascimento no formato inválido.");
            } catch (Exception e) {
                System.out.println("Erro ao atualizar residente: " + e.getMessage());
            }
    }

    public static void excluirResidente(PessoaController pController, Scanner scanner) {
        System.out.println("\n--- Exclusão de Residente ---");

        System.out.print("Insira o cpf do Residente: ");
        String cpf = scanner.nextLine().trim();

        if (!Validadores.ValidaCpf(cpf)) {
            System.out.println("CPF no formato inválido.");
            return;
        }

        Residente residente = pController.obterResidente(cpf);

        if (residente == null) {            
            System.out.println("Residente não encontrado");
            return;
        }

        System.out.println("Residente encontrado: " + residente.getNome());
        System.out.println("Tem certeza que deseja deletar este residente? (s/n): ");
        String confirmacao = scanner.nextLine().toUpperCase();

        if (confirmacao.equals("S")) {
            try {
                pController.removerResidente(cpf);
                System.out.println("Residente excluído com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao excluir residente: " + e.getMessage());
            }
        } else {
            System.out.println("Operação de exclusão cancelada.");
        }
    }

    // Informações de Residente
    public static void exibirInfoResidente(Residente residente) {
        System.out.println("Nome: " + residente.getNome() + 
                           "\nContato: " + residente.getContato());
    }


    public static void cadastrarVisitante(PessoaController pController, Scanner scanner) {
        System.out.println("\n--- Cadastro de Visitante ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }
        telefone = telefone.trim().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");


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

        Residente residente = pController.obterResidentePorBlocoEApartamento(blocoVisita, apartamentoVisita);

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
        } else if (confirmaResidente != 1) {
            System.out.println("Opção inválida.");
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

    public static void cadastrarVisitante(String nome, String telefone, int blocoVisita, int apartamentoVisita, PessoaController pController, Scanner scanner) {

        if (nome.isEmpty() || telefone.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos.");
            return;
        }

        Residente residente = pController.obterResidentePorBlocoEApartamento(blocoVisita, apartamentoVisita);

        if (residente == null) {
            System.out.println("Residente não encontrado!");
            return;
        }

        System.out.print("Visitante vinculado ao residente " + residente.getNome() + "\n");


        Visitante visitante = new Visitante(nome, telefone);
        visitante.setResidente(residente);

        visitante.setId(pController.gerarIdVisitante());

        if (pController.cadastrarVisitante(visitante)) {
            System.out.println("Visitante cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao registrar visitante");
        }
    }

    public static void atualizaVisitante(PessoaController pController, Scanner scanner){
        System.out.println("\n--- Atualização de Visitante ---");

        System.out.print("Insira o telefone do Visitante: ");
        String telefone = scanner.nextLine();
        telefone = telefone.trim().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");


        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }

        Visitante visitanteAntigo = pController.obterVisitatePorTelefone(telefone);

        if (visitanteAntigo == null) {
            System.out.println("Visitnte não encontrado!");
            return;
        }

        System.out.println("Visitante encontrado: " + visitanteAntigo.getNome());
        System.out.println("Gostaria de atualizar o registro? [s/n]");
        String opcao = scanner.nextLine().toUpperCase();

        if (!opcao.equals("S")) {
            System.out.println("Processo de atualização cancelado!");
            return;
        }

        try{
            System.out.println("Nome anterior:" + visitanteAntigo.getNome() + " \nNome novo: ");
            String nomeNovo = scanner.nextLine();

            System.out.println("Telefone anterior:" + visitanteAntigo.getContato() + " \nTelefone novo: ");
            System.out.print("Telefone: ");

            String telefoneNovo = scanner.nextLine().trim();

            if (!Validadores.ValidaContato(telefoneNovo)) {
                System.out.println("Telefone no formato inválido.");
                return;
            }

            pController.atualizarVisitante(visitanteAntigo, nomeNovo, telefoneNovo);
            System.out.println("\nAtualização concluida!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar visitante: " + e.getMessage());
        }
    };

    public static void excluirVisitante(PessoaController pController, Scanner scanner) {
        System.out.println("\n--- Exclusão de Visitante ---");

        System.out.print("Insira o telefone do Visitante: ");
        String telefone = scanner.nextLine().trim();

        if (!Validadores.ValidaContato(telefone)) {
            System.out.println("Telefone no formato inválido.");
            return;
        }

        Visitante visitante = pController.obterVisitatePorTelefone(telefone);

        if (visitante == null) {            
            System.out.println("Visitante não encontrado");
            return;
        }

        System.out.println("Visitante encontrado: " + visitante.getNome());
        System.out.println("Tem certeza que deseja deletar este visitante? (s/n): ");
        String confirmacao = scanner.nextLine().toUpperCase();


        if (confirmacao.equals("S")) {
            try{
                pController.removerVisitante(visitante.getId());
                System.out.println("Visitante excluido com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao excluir residente: " + e.getMessage());
            } 
        } else {
            System.out.println("Operação de exclusão cancelada.");
        }
    }

    public static void listarResidentes(PessoaController pController) {
        List<Residente> residentes = pController.listarResidentes();

        System.out.println("\n--- Lista de residentes ---");
        for (Residente m : residentes) {
            System.out.println("Nome: " + m.getNome() + 
                               ", CPF: " + m.getCpf() + 
                               ", Bloco: " + m.getBloco() +  
                               ", Apartamento: " + m.getApartamento() + 
                               ", Contato: " + m.getContato());
    
            if (m.getVeiculos() != null && !m.getVeiculos().isEmpty()) {
                System.out.println("Veículos:");
                for (Veiculo v : m.getVeiculos()) {
                    System.out.println("\tPlaca: " + v.getPlaca() + 
                                       ", Modelo: " + v.getModelo() + 
                                       ", Tipo: " + v.getTipo());
                }
            } else {
                System.out.println("Não possui veículos.");
            }
        }
    }

    public static void listarVisitantes(PessoaController pController) {
        List<Visitante> visitantes = pController.listarVisitantes();

        System.out.println("\n--- Lista de Visitantes ---");
        for (Visitante v : visitantes) {
            System.out.println("ID: " + v.getId() + 
                                ", Nome: " + v.getNome() + 
                                ", Contato: " + v.getContato());
        }
    }

    // Informações de Visitante
    public static void exibirInfoVisitante(Visitante visitante, PessoaController pController) {
        System.out.println("Nome: " + visitante.getNome() + 
                            "\nContato: " + visitante.getContato());
        Residente residente = pController.obterResidente(visitante.getResidente().getCpf());
        System.out.println("Visitando: ");
        exibirInfoResidente(residente);
    }
}