import java.util.Scanner;

import controller.AcessoController;
import controller.EntregaController;
import controller.PessoaController;
import controller.ReservaController;
import controller.VeiculoController;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AcessoController acessoController = new AcessoController();
        EntregaController entregaController = new EntregaController();
        PessoaController pessoaController = new PessoaController();
        ReservaController reservaController = new ReservaController();
        VeiculoController veiculoController = new VeiculoController();

        view.ExibirMenu.exibirMenuPrincipal(scanner, acessoController, entregaController, pessoaController, reservaController, veiculoController);

        scanner.close();
    }
}