package view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import controller.EntregaController;
import controller.PessoaController;
import model.Entrega;
import model.Residente;
import util.Validadores;

public class EntregaView {
    
    // Método para exibir os detalhes de uma entrega
    public static void registrarEntrega(EntregaController eController, PessoaController pController, Scanner scanner) throws Exception {
        System.out.println("\n--- Registro de entrega  ---");

        System.out.print("Insira o endereço para entrega \nBloco:");
        int bloco = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Apartamento:");
        int apto = scanner.nextInt();
        scanner.nextLine();

        try {
            Residente destinatario = pController.obterResidentePorBlocoEApartamento(bloco, apto);
            System.out.println("Entrega para o residente: " + destinatario.getNome() + "? [1 - sim, 2 - não]" );
    
            int confirmaResidente = scanner.nextInt();
            scanner.nextLine();
    
            if (confirmaResidente == 1) {
                System.out.println("Descrição da entrega:");
                String descricao = scanner.nextLine();
    
                Entrega entrega = new Entrega(eController.gerarIdEntrega(), destinatario, descricao, LocalDateTime.now());
    
                eController.adicionarEntrega(entrega);
            }
            
        } catch (Exception e) {
            System.out.println("ERRO AO REGISTRAR ENTREGA");
        }
    }

    public static void registrarRetirada(EntregaController eController, PessoaController pController, Scanner scanner){
       
        System.out.println("\n--- Registro de retirada  ---");

        System.out.print("Cpf do residente: ");
        String cpf = scanner.nextLine().trim();

        if (!Validadores.ValidaCpf(cpf) || cpf.isEmpty()) {
            System.out.println("CPF inválido.");
            return;
        }

        List<Entrega> entregasEncontradas = eController.listarEntregasNaoRetiradasPorResidente(cpf);

        System.out.println("Entregas encontradas para o residente:");
        for (Entrega e : entregasEncontradas) {
            System.out.println( "ID: " + e.getId() + 
                                ", Data recebida: " + e.getDataRecebimento() + 
                                ", Destinatario: " + e.getResidente().getNome() + 
                                ", Cpf: " + e.getResidente().getCpf() + 
                                ", Descrição: " + e.getDescricao());

            System.out.println("\nRetirar encomenda? [1 - sim, 2 - não]");
            int confirmaRetirada =scanner.nextInt();
            scanner.nextLine();

            if(confirmaRetirada == 1){
                e.setRetirada(true);
                System.out.println("Encomenda retirada com sucesso! ");
            }

            else if (confirmaRetirada != 1) {
                e.setRetirada(false);
                System.out.println("Encomenda não retirada! ");
            }
        }
       
        
    }

    public static void exibirlistarTodasEntregasNaoRetiradas(EntregaController eController) { 
        List<Entrega> entregasNaoRetiradas = eController.listarTodasEntregasNaoRetiradas();

        if (entregasNaoRetiradas.size() == 0)
            System.out.println("Não há dados de entregas não retiradas"); 

        for (Entrega e : entregasNaoRetiradas) {
            System.out.println( "ID: " + e.getId() + 
                                ", Data recebida: " + e.getDataRecebimento() + 
                                ", Destinatario: " + e.getResidente().getNome() + 
                                ", Cpf: " + e.getResidente().getCpf() + 
                                ", Descrição: " + e.getDescricao() +
                                ", Retirado: " + e.isRetirada()
                                );
        }
    }


    // Método para exibir uma lista de entregas
    public static void exibirListaEntregas(EntregaController eController) { 
        List<Entrega> entregasRealizadas = eController.listarEntregasRealizadas();

        if (entregasRealizadas.size() == 0)
            System.out.println("Não há dados de entregas realizadas"); 

        for (Entrega e : entregasRealizadas) {
            System.out.println( "ID: " + e.getId() + 
                                ", Data recebida: " + e.getDataRecebimento() + 
                                ", Destinatario: " + e.getResidente().getNome() + 
                                ", Descrição: " + e.getDescricao() +
                                ", Retirado: " + e.isRetirada());
        }
     }


}
