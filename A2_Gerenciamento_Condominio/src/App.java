/*Grupo dos alunos: 

André Lucas Lencina Peixoto
RGM: 33827311

Camila Eduarda Dell'Antonio Perez Kozarewicz 
RGM: 34653694

João Antônio de Oliveira Armstrong 
RGM: 34600612
 */

import java.util.ArrayList;
import java.util.Scanner;

import controller.AcessoController;
import controller.EntregaController;
import controller.PessoaController;
import controller.ReservaController;
import controller.VeiculoController;
import model.Acesso;
import model.Entrega;
import model.Reserva;
import model.Residente;
import model.Veiculo;
import model.Visitante;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AcessoController acessoController = new AcessoController(new ArrayList<Acesso>());
        EntregaController entregaController = new EntregaController(new ArrayList<Entrega>());
        PessoaController pessoaController = new PessoaController(new ArrayList<Residente>(), new ArrayList<Visitante>());
        ReservaController reservaController = new ReservaController(new ArrayList<Reserva>());
        VeiculoController veiculoController = new VeiculoController(new ArrayList<Veiculo>());

        view.ExibirMenu.exibirMenuPrincipal(scanner, acessoController, entregaController, pessoaController, reservaController, veiculoController);

        scanner.close();
    }
}