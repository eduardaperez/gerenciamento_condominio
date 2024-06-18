import java.util.Scanner;

import controller.AcessoController;
import controller.EntregaController;
import controller.PessoaController;
import controller.RelatorioController;
import controller.ReservaController;
import controller.VeiculoController;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AcessoController acessoController = new AcessoController();
        EntregaController entregaController = new EntregaController();
        PessoaController pessoaController = new PessoaController();
        RelatorioController relatorioController = new RelatorioController();
        ReservaController reservaController = new ReservaController();
        VeiculoController veiculoController = new VeiculoController();

        view.ExibirMenu.exibirMenuPrincipal(scanner, acessoController, entregaController, pessoaController, relatorioController, reservaController, veiculoController);

        scanner.close();
    }
}