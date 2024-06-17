package view;

import model.Residente;
import model.Veiculo;
import model.Visitante;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class PessoaView {
    private Scanner scanner = new Scanner(System.in);

    public Residente cadastraRresidente() {

        Residente residente = new Residente(null, null);

        // Setando valores para cadastro
        System.out.println("\n--- Cadastro de residente ---");
        System.out.print("Nome: ");
        residente.setNome(scanner.nextLine());
        System.out.print("Telefone: ");
        residente.setContato(scanner.nextLine());
        System.out.print("Cpf: ");
        residente.setCpf(scanner.nextLine());
        System.out.print("Bloco: ");
        residente.setBloco(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Apartamento: ");
        residente.setApartamento(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Vaga: ");
        residente.setVaga(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        residente.setDataNascimento(LocalDate.parse(scanner.nextLine().replace("/","-")));

        // Condição para quem possui veiculo, aciona o CadastraVeiculo e vincula ao residente, exemplo a seguir
        // System.out.print("Possui veiculo? [0 - não, 1 - sim]");
        // Logica para se foi sim, chamar o método cadastrar residente e setar o objeto para o residente.setVeiculos();
        
        return residente;
    }

    public Visitante cadastrarVisitante() {

        Visitante visitante = new Visitante(null, null);

        System.out.println("\n--- Cadastro de Visitante ---");

        System.out.print("Nome: ");
        visitante.setNome(scanner.nextLine());
        System.out.print("Telefone: ");
        visitante.setContato(scanner.nextLine());
        System.out.print("Esta visitando o bloco: ");
        int blocoVisita = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Apartamento: ");
        int apartamentoVisita = scanner.nextInt();
        scanner.nextLine();

        // executa método de busca de visitante por bloco e apartamento
        // try buscaResidente(), se não imprime resindente não encontrado
        // visitante.setResidente();

        return visitante;
    }

    public void listarResidentes(List<Residente> residentes) {
        System.out.println("\n--- Lista de residentees ---");
        for (Residente m : residentes) {
            System.out.println("Nome: " + m.getNome() + ", CPF: " + m.getCpf() + ", Bloco: " + m.getBloco() +  ", Apartamento: " + m.getApartamento() + ", Contato: " + m.getContato());
        }
    }

    public void listarVisitantes(List<Visitante> visitantes) {
        System.out.println("\n--- Lista de Visitantes ---");
        for (Visitante v : visitantes) {
            System.out.println("ID: " + v.getId() + ", Nome: " + v.getNome() + ", Contato: " + v.getContato() + ", Telefone: ");
        }
    }
}